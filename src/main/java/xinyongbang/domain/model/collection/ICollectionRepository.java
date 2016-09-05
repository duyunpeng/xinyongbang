package xinyongbang.domain.model.collection;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/5/24.
 */
public interface ICollectionRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
