package xinyongbang.domain.model.permission;


import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IPermissionRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Permission searchByName(String name, String appKey);
}
