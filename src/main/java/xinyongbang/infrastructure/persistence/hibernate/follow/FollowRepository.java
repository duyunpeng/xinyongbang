package xinyongbang.infrastructure.persistence.hibernate.follow;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.follow.Follow;
import xinyongbang.domain.model.follow.IFollowRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by dw on 2016/6/12.
 */
@Repository("followRepository")
public class FollowRepository extends AbstractHibernateGenericRepository<Follow, String>
        implements IFollowRepository<Follow, String> {

    @Override
    public Follow searchByUser(String user, String followUser) {
        Criteria criteria = this.getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("user.id", user));
        criteria.add(Restrictions.eq("followUser.userName", followUser));
        criteria.createAlias("followUser", "followUser");
        return (Follow) criteria.uniqueResult();
    }
}
