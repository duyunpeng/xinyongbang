package xinyongbang.domain.service.sign;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.golddetailed.command.CreateGoldDetailedCommand;
import xinyongbang.application.sign.command.SignCommand;
import xinyongbang.core.enums.FlowType;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.util.CoreDateUtils;
import xinyongbang.domain.model.sign.ISignRepository;
import xinyongbang.domain.model.sign.Sign;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.golddetailed.IGoldDetailedService;
import xinyongbang.domain.service.user.IUserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/4/29.
 */
@Service("signService")
public class SignService implements ISignService {

    @Autowired
    private ISignRepository<Sign, String> signRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoldDetailedService goldDetailedService;

    @Override
    public void apiSign(SignCommand command) {
        User user = userService.searchByID(command.getUser());
        if (!this.checkDate(user)) {
            user.changeGold(user.getGold().add(new BigDecimal(1)));
            userService.update(user);

            Sign sign = new Sign(user);
            signRepository.save(sign);

            CreateGoldDetailedCommand goldDetailedCommand = new CreateGoldDetailedCommand();
            goldDetailedCommand.setUser(user.getId());
            goldDetailedCommand.setGoldNumber(new BigDecimal(1));
            goldDetailedCommand.setFlowType(FlowType.IN_FLOW);
            goldDetailedCommand.setDescription("签到");
            goldDetailedService.create(goldDetailedCommand);
        } else {
            throw new ExistException("今日已经签到");
        }
    }

    @Override
    public boolean checkDate(User user) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("user.id", user.getId()));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        List<Sign> signList = signRepository.list(criterionList, orderList);
        Sign sign = signList.size() > 0 ? signList.get(0) : null;
        if (null == sign) {
            return false;
        } else {
            return CoreDateUtils.formatDate(sign.getCreateDate(), "yyyy-MM-dd").equals(CoreDateUtils.formatDate(new Date(), "yyyy-MM-dd")) ? true : false;
        }
    }

    @Override
    public boolean apiJudgeSign(SignCommand command) {
        User user = userService.searchByID(command.getUser());
        return this.checkDate(user);
    }
}
