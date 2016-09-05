package xinyongbang.application.article;

import xinyongbang.application.article.command.CreateArticleCommand;
import xinyongbang.application.article.command.EditArticleCommand;
import xinyongbang.application.article.command.ListArticleCommand;
import xinyongbang.application.article.representation.ArticleRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.article.Article;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by YJH on 2016/4/19.
 */
public interface IArticleAppService {
    Pagination<ArticleRepresentation> pagination(ListArticleCommand command);

    ArticleRepresentation searchByID(String id);

    ArticleRepresentation create(CreateArticleCommand command);

    ArticleRepresentation edit(EditArticleCommand command);

    void updateStatus(SharedCommand command);
}
