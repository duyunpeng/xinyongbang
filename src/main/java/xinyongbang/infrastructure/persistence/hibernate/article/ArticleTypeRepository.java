package xinyongbang.infrastructure.persistence.hibernate.article;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.article.ArticleType;
import xinyongbang.domain.model.article.IArticleTypeRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("articleTypeRepository")
public class ArticleTypeRepository extends AbstractHibernateGenericRepository<ArticleType, String>
        implements IArticleTypeRepository<ArticleType, String> {
    @Override
    public ArticleType searchByName(String name) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("name", name));
        return (ArticleType) criteria.uniqueResult();
    }
}
