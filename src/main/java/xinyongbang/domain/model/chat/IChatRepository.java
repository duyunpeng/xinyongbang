package xinyongbang.domain.model.chat;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IChatRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
