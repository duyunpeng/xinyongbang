package xinyongbang.application.article.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.article.representation.ApiArticleRepresentation;
import xinyongbang.application.article.representation.ArticleTypeRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.article.Article;

/**
 * Created by YJH on 2016/5/18.
 */
@Component
public class ApiArticleRepresentationMapper extends CustomMapper<Article, ApiArticleRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Article article, ApiArticleRepresentation representation, MappingContext context) {
        if (null != article.getAccount()) {
            representation.setAccount(article.getAccount().getId());
            representation.setUserName(article.getAccount().getUserName());
        }
        if (null != article.getType()) {
            ArticleTypeRepresentation data = mappingService.map(article.getType(), ArticleTypeRepresentation.class, false);
            representation.setType(data);
        }
    }
}
