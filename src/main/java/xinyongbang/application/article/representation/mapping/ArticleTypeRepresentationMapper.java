package xinyongbang.application.article.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import xinyongbang.application.article.representation.ArticleTypeRepresentation;
import xinyongbang.domain.model.article.ArticleType;

/**
 * Created by YJH on 2016/4/19.
 */
@Component
public class ArticleTypeRepresentationMapper extends CustomMapper<ArticleType, ArticleTypeRepresentation> {
}
