package xinyongbang.infrastructure.persistence.hibernate.article;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.article.Article;
import xinyongbang.domain.model.article.IArticleRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("articleRepository")
public class ArticleRepository extends AbstractHibernateGenericRepository<Article, String>
        implements IArticleRepository<Article, String> {
}
