package xinyongbang.infrastructure.persistence.hibernate.identitycard;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.identitycard.IIdentityCardRepository;
import xinyongbang.domain.model.identitycard.IdentityCard;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("identityCardRepository")
public class IdentityCardRepository extends AbstractHibernateGenericRepository<IdentityCard, String>
        implements IIdentityCardRepository<IdentityCard, String> {

}
