package xinyongbang.domain.service.article;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.article.command.CreateArticleTypeCommand;
import xinyongbang.application.article.command.EditArticleTypeCommand;
import xinyongbang.application.article.command.ListArticleTypeCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.enums.EnableStatus;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.article.ArticleType;
import xinyongbang.domain.model.article.IArticleTypeRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
@Service("articleTypeService")
public class ArticleTypeService implements IArticleTypeService {

    @Autowired
    private IArticleTypeRepository<ArticleType, String> articleTypeRepository;

    @Override
    public Pagination<ArticleType> pagination(ListArticleTypeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getName())) {
            criterionList.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return articleTypeRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public List<ArticleType> list(ListArticleTypeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return articleTypeRepository.list(criterionList, orderList);
    }

    @Override
    public ArticleType create(CreateArticleTypeCommand command) {
        if (null != this.searchByName(command.getName())) {
            throw new ExistException("name[" + command.getName() + "]的ArticleType数据已存在");
        }
        ArticleType articleType = new ArticleType(command.getName(), command.getStatus());
        articleTypeRepository.save(articleType);
        return articleType;
    }

    @Override
    public ArticleType searchByID(String id) {
        ArticleType articleType = articleTypeRepository.getById(id);
        if (null == articleType) {
            throw new NoFoundException("没有找到ID[" + id + "]的ArticleType数据");
        }
        return articleType;
    }

    @Override
    public ArticleType searchByName(String name) {
        return articleTypeRepository.searchByName(name);
    }

    @Override
    public ArticleType edit(EditArticleTypeCommand command) {
        ArticleType articleType = this.searchByID(command.getId());
        articleType.fainWhenConcurrencyViolation(command.getVersion());
        if (!articleType.getName().equals(command.getName())) {
            if (null != this.searchByName(command.getName())) {
                throw new ExistException("name[" + command.getName() + "]的ArticleType数据已存在");
            }
        }

        articleType.changeName(command.getName());
        articleTypeRepository.update(articleType);
        return articleType;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        ArticleType articleType = this.searchByID(command.getId());
        articleType.fainWhenConcurrencyViolation(command.getVersion());
        if (articleType.getStatus() == EnableStatus.DISABLE) {
            articleType.changeStatus(EnableStatus.ENABLE);
        } else {
            articleType.changeStatus(EnableStatus.DISABLE);
        }
        articleTypeRepository.update(articleType);
    }

}
