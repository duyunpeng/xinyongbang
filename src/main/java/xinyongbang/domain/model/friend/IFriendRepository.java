package xinyongbang.domain.model.friend;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IFriendRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Friend searchByUser(String user, String friend);
}
