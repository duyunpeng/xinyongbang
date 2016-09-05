package xinyongbang.infrastructure.persistence.hibernate.collection;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.collection.Collection;
import xinyongbang.domain.model.collection.ICollectionRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/5/24.
 */
@Repository("collectionRepository")
public class CollectionRepository extends AbstractHibernateGenericRepository<Collection, String>
        implements ICollectionRepository<Collection, String> {

}
