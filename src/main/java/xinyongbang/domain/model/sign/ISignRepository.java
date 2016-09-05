package xinyongbang.domain.model.sign;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/15.
 */
public interface ISignRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
