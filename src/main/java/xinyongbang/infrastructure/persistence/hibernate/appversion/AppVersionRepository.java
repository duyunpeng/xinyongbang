package xinyongbang.infrastructure.persistence.hibernate.appversion;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.appversion.AppVersion;
import xinyongbang.domain.model.appversion.IAppVersionRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("appVersionRepository")
public class AppVersionRepository extends AbstractHibernateGenericRepository<AppVersion, String>
        implements IAppVersionRepository<AppVersion, String> {
    @Override
    public AppVersion getByAppVersion(String appVersion) {
        Criteria criteria=getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("appVersion",appVersion));
        return (AppVersion) criteria.uniqueResult();
    }
}
