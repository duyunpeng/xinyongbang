package xinyongbang.application.article;

import xinyongbang.application.article.command.ListArticleCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by dyp on 2016/6/12.
 */
public interface IApiArticleAppService {

    ApiResponse articleList(ListArticleCommand command);
}
