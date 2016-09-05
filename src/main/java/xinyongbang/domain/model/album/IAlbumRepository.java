package xinyongbang.domain.model.album;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/5/13.
 */
public interface IAlbumRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
