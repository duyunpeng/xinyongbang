package xinyongbang.infrastructure.persistence.hibernate.clicklike;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.clicklike.ClickLike;
import xinyongbang.domain.model.clicklike.IClickLikeRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("clickLikeRepository")
public class ClickLikeRepository extends AbstractHibernateGenericRepository<ClickLike, String>
        implements IClickLikeRepository<ClickLike, String> {

    @Override
    public ClickLike searchByUser(String user, String clickUser) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("user.id", user));
        criteria.add(Restrictions.eq("clickUser.id", clickUser));
        return (ClickLike) criteria.uniqueResult();
    }
}
