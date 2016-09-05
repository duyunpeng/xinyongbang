package xinyongbang.infrastructure.persistence.hibernate.sign;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.sign.ISignRepository;
import xinyongbang.domain.model.sign.Sign;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("signRepository")
public class SignRepository extends AbstractHibernateGenericRepository<Sign, String>
        implements ISignRepository<Sign,String> {
}
