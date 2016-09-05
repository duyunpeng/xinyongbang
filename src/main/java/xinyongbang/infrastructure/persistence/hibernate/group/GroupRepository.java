package xinyongbang.infrastructure.persistence.hibernate.group;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.model.group.IGroupRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/5/13.
 */
@Repository("groupRepository")
public class GroupRepository extends AbstractHibernateGenericRepository<Group, String>
        implements IGroupRepository<Group, String> {
    @Override
    public Group searchByNO(String groupNo) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("groupNo", groupNo));
        return (Group) criteria.uniqueResult();
    }
}
