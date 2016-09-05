package xinyongbang.application.article.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.account.representation.AccountRepresentation;
import xinyongbang.application.article.representation.ArticleRepresentation;
import xinyongbang.application.article.representation.ArticleTypeRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.article.Article;

/**
 * Created by YJH on 2016/4/19.
 */
@Component
public class ArticleRepresentationMapper extends CustomMapper<Article, ArticleRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Article article, ArticleRepresentation representation, MappingContext context) {
        if (null != article.getAccount()) {
            AccountRepresentation data = mappingService.map(article.getAccount(), AccountRepresentation.class, false);
            representation.setAccount(data);
        }
        if (null != article.getType()) {
            ArticleTypeRepresentation data = mappingService.map(article.getType(), ArticleTypeRepresentation.class, false);
            representation.setType(data);
        }
    }

}
