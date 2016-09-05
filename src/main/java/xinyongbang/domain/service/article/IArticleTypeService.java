package xinyongbang.domain.service.article;

import xinyongbang.application.article.command.CreateArticleTypeCommand;
import xinyongbang.application.article.command.EditArticleTypeCommand;
import xinyongbang.application.article.command.ListArticleTypeCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.article.ArticleType;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
public interface IArticleTypeService {

    Pagination<ArticleType> pagination(ListArticleTypeCommand command);

    List<ArticleType> list(ListArticleTypeCommand command);

    ArticleType create(CreateArticleTypeCommand command);

    ArticleType searchByID(String id);

    ArticleType searchByName(String name);

    ArticleType edit(EditArticleTypeCommand command);

    void updateStatus(SharedCommand command);
}
