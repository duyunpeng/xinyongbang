package xinyongbang.infrastructure.persistence.hibernate.idcard;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.idcard.IIdCardRepository;
import xinyongbang.domain.model.idcard.IdCard;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by dyp on 2016/5/19.
 */
@Repository("idCardRepository")
public class IdCardRepository extends AbstractHibernateGenericRepository<IdCard,String>
        implements IIdCardRepository<IdCard,String>{

    @Override
    public IdCard searchByCardNumber(String cardNumber) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("cardNumber",cardNumber));
        return (IdCard) criteria.uniqueResult();
    }
}
