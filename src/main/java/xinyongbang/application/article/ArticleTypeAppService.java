package xinyongbang.application.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.article.command.CreateArticleTypeCommand;
import xinyongbang.application.article.command.EditArticleTypeCommand;
import xinyongbang.application.article.command.ListArticleTypeCommand;
import xinyongbang.application.article.representation.ArticleTypeRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.article.ArticleType;
import xinyongbang.domain.service.article.IArticleTypeService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
@Service("articleTypeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ArticleTypeAppService implements IArticleTypeAppService {

    @Autowired
    private IArticleTypeService articleTypeService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<ArticleTypeRepresentation> pagination(ListArticleTypeCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<ArticleType> pagination = articleTypeService.pagination(command);
        List<ArticleTypeRepresentation> data = mappingService.mapAsList(pagination.getData(), ArticleTypeRepresentation.class);
        return new Pagination<ArticleTypeRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public ArticleTypeRepresentation create(CreateArticleTypeCommand command) {
        return mappingService.map(articleTypeService.create(command), ArticleTypeRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleTypeRepresentation searchByID(String id) {
        return mappingService.map(articleTypeService.searchByID(id), ArticleTypeRepresentation.class, false);
    }

    @Override
    public ArticleTypeRepresentation edit(EditArticleTypeCommand command) {
        return mappingService.map(articleTypeService.edit(command), ArticleTypeRepresentation.class, false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        articleTypeService.updateStatus(command);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleTypeRepresentation> listJSON(ListArticleTypeCommand command) {
        return mappingService.mapAsList(articleTypeService.list(command), ArticleTypeRepresentation.class);
    }
}
