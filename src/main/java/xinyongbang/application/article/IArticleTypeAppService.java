package xinyongbang.application.article;

import xinyongbang.application.article.command.CreateArticleTypeCommand;
import xinyongbang.application.article.command.EditArticleTypeCommand;
import xinyongbang.application.article.command.ListArticleTypeCommand;
import xinyongbang.application.article.representation.ArticleTypeRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.article.ArticleType;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
public interface IArticleTypeAppService {

    Pagination<ArticleTypeRepresentation> pagination(ListArticleTypeCommand command);

    ArticleTypeRepresentation create(CreateArticleTypeCommand command);

    ArticleTypeRepresentation searchByID(String id);

    ArticleTypeRepresentation edit(EditArticleTypeCommand command);

    void updateStatus(SharedCommand command);

    List<ArticleTypeRepresentation> listJSON(ListArticleTypeCommand command);
}
