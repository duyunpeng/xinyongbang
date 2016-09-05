package xinyongbang.infrastructure.persistence.hibernate.friend;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.friend.Friend;
import xinyongbang.domain.model.friend.IFriendRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/22.
 */
@Repository("friendRepository")
public class FriendRepository extends AbstractHibernateGenericRepository<Friend, String>
        implements IFriendRepository<Friend, String> {
    @Override
    public Friend searchByUser(String user, String friend) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("user.id", user));
        criteria.add(Restrictions.eq("friend.userName", friend));
        criteria.createAlias("friend", "friend");
        return (Friend) criteria.uniqueResult();
    }
}
