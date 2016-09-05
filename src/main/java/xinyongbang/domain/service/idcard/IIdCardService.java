package xinyongbang.domain.service.idcard;

import xinyongbang.application.idcard.command.ListIdCardCommand;
import xinyongbang.domain.model.idcard.IdCard;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by dyp on 2016/5/20.
 */
public interface IIdCardService {

    Pagination<IdCard> pagination(ListIdCardCommand command);

    IdCard searchByID(String id);

    IdCard searchByCardNumber(String cardNumber);

    void create(IdCard idCard);
}
