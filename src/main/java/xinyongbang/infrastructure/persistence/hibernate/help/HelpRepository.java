package xinyongbang.infrastructure.persistence.hibernate.help;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.account.Account;
import xinyongbang.domain.model.help.Help;
import xinyongbang.domain.model.help.IHelpRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by dw on 2016/5/23.
 */
@Repository("helpRepository")
public class HelpRepository extends AbstractHibernateGenericRepository<Help, String>
        implements IHelpRepository<Help, String>{

    @Override
    public Help getByTitle(String title) {
        Criteria criteria=getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("title",title));
        return (Help) criteria.uniqueResult();
    }
}
