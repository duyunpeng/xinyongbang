package xinyongbang.domain.model.groupverify;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/5/25.
 */
public interface IGroupVerifyRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
