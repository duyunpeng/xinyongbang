package xinyongbang.application.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.article.command.ListArticleCommand;
import xinyongbang.application.article.representation.ArticleRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.article.Article;
import xinyongbang.domain.service.article.IArticleService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 2016/6/12.
 */
@Service("apiArticleAppService")
public class ApiArticleAppService implements IApiArticleAppService{

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IArticleService articleService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse articleList(ListArticleCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Article> pagination = articleService.apiArticleList(command);
        List<ArticleRepresentation> data = mappingService.mapAsList(pagination.getData(),ArticleRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS,ApiReturnCode.SUCCESS.getName(),
                new Pagination<ArticleRepresentation>(data,pagination.getCount(),pagination.getCount(),pagination.getPage()));
    }
}
