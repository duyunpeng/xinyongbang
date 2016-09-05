package xinyongbang.application.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.article.command.CreateArticleCommand;
import xinyongbang.application.article.command.EditArticleCommand;
import xinyongbang.application.article.command.ListArticleCommand;
import xinyongbang.application.article.representation.ArticleRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.article.Article;
import xinyongbang.domain.service.article.IArticleService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
@Service("articleAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ArticleAppService implements IArticleAppService {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<ArticleRepresentation> pagination(ListArticleCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Article> pagination = articleService.pagination(command);
        List<ArticleRepresentation> date = mappingService.mapAsList(pagination.getData(), ArticleRepresentation.class);
        return new Pagination<ArticleRepresentation>(date, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleRepresentation searchByID(String id) {
        return mappingService.map(articleService.searchByID(id), ArticleRepresentation.class, false);
    }

    @Override
    public ArticleRepresentation create(CreateArticleCommand command) {
        return mappingService.map(articleService.create(command), ArticleRepresentation.class, false);
    }

    @Override
    public ArticleRepresentation edit(EditArticleCommand command) {
        return mappingService.map(articleService.edit(command), ArticleRepresentation.class, false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        articleService.updateStatus(command);
    }
}
