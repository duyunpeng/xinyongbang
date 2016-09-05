package xinyongbang.domain.model.follow;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by dw on 2016/6/12.
 */
public interface IFollowRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    Follow searchByUser(String user, String followUser);
}
