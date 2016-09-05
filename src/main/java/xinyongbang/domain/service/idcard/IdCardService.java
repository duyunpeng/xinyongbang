package xinyongbang.domain.service.idcard;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.idcard.command.ListIdCardCommand;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.idcard.IIdCardRepository;
import xinyongbang.domain.model.idcard.IdCard;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyp on 2016/5/20.
 */
@Service("idCardService")
public class IdCardService implements IIdCardService {

    @Autowired
    private IIdCardRepository<IdCard, String> idCardRepository;

    @Override
    public Pagination<IdCard> pagination(ListIdCardCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getName())) {
            criterionList.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getCardNumber())) {
            criterionList.add(Restrictions.like("cardNumber", command.getCardNumber(), MatchMode.ANYWHERE));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return idCardRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public IdCard searchByID(String id) {
        IdCard idCard = idCardRepository.getById(id);
        if (null == idCard) {
            throw new NoFoundException("没有找到ID["+ id +"]的idCard数据");
        }
        return idCard;
    }

    @Override
    public IdCard searchByCardNumber(String cardNumber) {
        return idCardRepository.searchByCardNumber(cardNumber);
    }

    @Override
    public void create(IdCard idCard) {
        idCardRepository.save(idCard);
    }
}
