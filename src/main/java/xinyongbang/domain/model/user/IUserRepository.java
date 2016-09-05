package xinyongbang.domain.model.user;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/15.
 */
public interface IUserRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    User searchByName(String userName);

    void addLock();
}
