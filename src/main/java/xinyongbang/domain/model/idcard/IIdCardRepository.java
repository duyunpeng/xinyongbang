package xinyongbang.domain.model.idcard;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by dyp on 2016/5/19.
 */
public interface IIdCardRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    IdCard searchByCardNumber(String cardNumber);
}
