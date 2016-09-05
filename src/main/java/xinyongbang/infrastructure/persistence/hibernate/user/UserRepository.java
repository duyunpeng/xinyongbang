package xinyongbang.infrastructure.persistence.hibernate.user;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.user.IUserRepository;
import xinyongbang.domain.model.user.User;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("userRepository")
public class UserRepository extends AbstractHibernateGenericRepository<User, String>
        implements IUserRepository<User, String> {
    @Override
    public User searchByName(String userName) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("userName", userName));
        return (User) criteria.uniqueResult();
    }

    @Override
    public void addLock() {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.setLockMode(LockMode.WRITE);
    }
}
