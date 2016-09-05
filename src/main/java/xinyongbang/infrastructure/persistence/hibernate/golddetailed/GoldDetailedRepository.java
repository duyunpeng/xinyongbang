package xinyongbang.infrastructure.persistence.hibernate.golddetailed;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.golddetailed.GoldDetailed;
import xinyongbang.domain.model.golddetailed.IGoldDetailedRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("goldDetailedRepository")
public class GoldDetailedRepository extends AbstractHibernateGenericRepository<GoldDetailed, String>
        implements IGoldDetailedRepository<GoldDetailed, String> {
}
