package xinyongbang.domain.model.help;

import xinyongbang.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by dw on 2016/5/23.
 */
public interface IHelpRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
        Help getByTitle(String title) ;
}
