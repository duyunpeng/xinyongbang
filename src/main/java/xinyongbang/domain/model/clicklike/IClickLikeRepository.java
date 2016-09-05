package xinyongbang.domain.model.clicklike;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/15.
 */
public interface IClickLikeRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    ClickLike searchByUser(String user, String clickUser);
}
