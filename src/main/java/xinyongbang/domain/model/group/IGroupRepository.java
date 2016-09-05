package xinyongbang.domain.model.group;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/5/13.
 */
public interface IGroupRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Group searchByNO(String groupNo);
}
