package xinyongbang.domain.service.identitycard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.identitycard.command.*;
import xinyongbang.application.picture.command.CreatePictureCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.common.CharsetConstant;
import xinyongbang.core.common.Constants;
import xinyongbang.core.enums.AuthStatus;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.IdentityCardVerifyException;
import xinyongbang.core.exception.IdentityCardVerifyNotEqualException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.core.util.CoreHttpUtils;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.idcard.IdCard;
import xinyongbang.domain.model.identitycard.IIdentityCardRepository;
import xinyongbang.domain.model.identitycard.IdentityCard;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.idcard.IIdCardService;
import xinyongbang.domain.service.picture.IPictureService;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyp on 2016/4/20.
 */
@Service("identityCardService")
public class IdentityCardService implements IIdentityCardService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IIdentityCardRepository<IdentityCard, String> iIdentityCardRepository;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IIdCardService idCardService;

    @Override
    public Pagination<IdentityCard> pagination(ListIdentityCardCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getCardNumber())) {
            criterionList.add(Restrictions.like("cardNumber", command.getCardNumber(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getName())) {
            criterionList.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return iIdentityCardRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public IdentityCard searchByID(String id) {
        IdentityCard identityCard = iIdentityCardRepository.getById(id);
        if (null == identityCard) {
            throw new NoFoundException("没有找到ID[" + id + "]的IdentityCard数据");
        }
        return identityCard;
    }

    @Override
    public IdentityCard create(CreateIdentityCardCommand command) {
        IdentityCard identityCard = new IdentityCard(command.getCardNumber(), command.getName(), command.getFrontPic(), command.getFrontPic(), command.getHandTakePic());
        iIdentityCardRepository.save(identityCard);
        return identityCard;
    }


    @Override
    public IdentityCard edit(EditIdentityCardCommand command) {
        IdentityCard identityCard = this.searchByID(command.getId());
        identityCard.fainWhenConcurrencyViolation(command.getVersion());
        iIdentityCardRepository.update(identityCard);
        return identityCard;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        IdentityCard identityCard = this.searchByID(command.getId());
        identityCard.fainWhenConcurrencyViolation(command.getVersion());
        iIdentityCardRepository.update(identityCard);
    }

    @Override
    public List<IdentityCard> list(ListIdentityCardCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("level"));
        orderList.add(Order.asc("sort"));
        return iIdentityCardRepository.list(criterionList, orderList);
    }

    @Override
    public void apiAttestation(AttestationIdentityCardCommand command) {
        User user = userService.searchByID(command.getUser());
        if (user.getAuthStatus() != AuthStatus.NOT) {
            throw new ExistException("已经认证或待审核中");
        }

        //先匹配数据库
        IdCard idCard = idCardService.searchByCardNumber(command.getCardNumber());
        if (null != idCard) {
            if (!idCard.getName().equals(command.getName())) {
                throw new IdentityCardVerifyNotEqualException("身份证信息错误");
            }
        } else {
            //调用API验证身份证
            command.setCardNumber(command.getCardNumber().toLowerCase());
            long timeMillis = System.currentTimeMillis();
            String md5_param = Constants.ID_CARD_MAIL_ID + command.getName() + command.getCardNumber() + timeMillis + Constants.ID_CARD_APPKEY;
            String sign = CoreStringUtils.md5(md5_param, 32, true, CharsetConstant.UTF8_STRING);
            String param = new StringBuffer().append("mall_id=" + Constants.ID_CARD_MAIL_ID)
                    .append("&realname=" + command.getName())
                    .append("&idcard=" + command.getCardNumber())
                    .append("&tm=" + timeMillis)
                    .append("&sign=" + sign).toString();
            String result = CoreHttpUtils.urlConnection(Constants.ID_CARD_URL, param);
            JSONObject jsonObject = JSON.parseObject(result);
            int status = jsonObject.getInteger("status");
            int code = jsonObject.getJSONObject("data").getInteger("code");
            String message = jsonObject.getJSONObject("data").getString("message");
            logger.info("调用身份证验证接口:" + message);
            if (status == 2001) {
                if (code == 1000) {

                } else if (code == 1001) {
                    throw new IdentityCardVerifyNotEqualException("身份证信息错误");
                } else {
                    throw new IdentityCardVerifyException("身份证信息验证失败");
                }
            } else {
                throw new IdentityCardVerifyException("身份证信息验证失败");
            }
        }

        CreatePictureCommand picCommand = fileUploadService.moveToImg(command.getFrontPic());
        picCommand.setDescribes("身份证正面图片");
        Picture frontPic = pictureService.create(picCommand);

        picCommand = fileUploadService.moveToImg(command.getBackPic());
        picCommand.setDescribes("身份证背面图片");
        Picture backPic = pictureService.create(picCommand);

        picCommand = fileUploadService.moveToImg(command.getHandTakePic());
        picCommand.setDescribes("手持身份证图片");
        Picture handTakePic = pictureService.create(picCommand);

        IdentityCard identityCard = new IdentityCard(command.getCardNumber(), command.getName(), frontPic, backPic, handTakePic);
        iIdentityCardRepository.save(identityCard);
        if (null == idCard) {
            idCardService.create(new IdCard(command.getCardNumber(), command.getName()));
        }

        user.changeAuthStatus(AuthStatus.IN);
        user.changeIdentityCard(identityCard);
        userService.update(user);
    }
}
