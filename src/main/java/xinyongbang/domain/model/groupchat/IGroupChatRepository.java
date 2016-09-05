package xinyongbang.domain.model.groupchat;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/5/16.
 */
public interface IGroupChatRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
