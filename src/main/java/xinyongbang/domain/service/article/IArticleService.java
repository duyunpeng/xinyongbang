package xinyongbang.domain.service.article;

import xinyongbang.application.article.command.CreateArticleCommand;
import xinyongbang.application.article.command.EditArticleCommand;
import xinyongbang.application.article.command.ListArticleCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.article.Article;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by YJH on 2016/4/19.
 */
public interface IArticleService {
    Pagination<Article> pagination(ListArticleCommand command);

    Article searchByID(String id);

    Article create(CreateArticleCommand command);

    Article edit(EditArticleCommand command);

    void updateStatus(SharedCommand command);

    Pagination<Article> apiArticleList(ListArticleCommand command);
}
