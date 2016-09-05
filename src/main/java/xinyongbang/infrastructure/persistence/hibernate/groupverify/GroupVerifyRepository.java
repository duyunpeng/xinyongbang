package xinyongbang.infrastructure.persistence.hibernate.groupverify;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.groupverify.GroupVerify;
import xinyongbang.domain.model.groupverify.IGroupVerifyRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/5/25.
 */
@Repository("groupVerifyRepository")
public class GroupVerifyRepository extends AbstractHibernateGenericRepository<GroupVerify, String>
        implements IGroupVerifyRepository<GroupVerify, String> {
}
