package xinyongbang.domain.service.article;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.article.command.CreateArticleCommand;
import xinyongbang.application.article.command.EditArticleCommand;
import xinyongbang.application.article.command.ListArticleCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.enums.EnableStatus;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.domain.model.account.Account;
import xinyongbang.domain.model.article.Article;
import xinyongbang.domain.model.article.ArticleType;
import xinyongbang.domain.model.article.IArticleRepository;
import xinyongbang.domain.service.account.IAccountService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
@Service("articleService")
public class ArticleService implements IArticleService {

    @Autowired
    private IArticleRepository<Article, String> articleRepository;

    @Autowired
    private IArticleTypeService articleTypeService;

    @Autowired
    private IAccountService accountService;

    @Override
    public Pagination<Article> pagination(ListArticleCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return articleRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Article searchByID(String id) {
        Article article = articleRepository.getById(id);
        if (null == article) {
            throw new NoFoundException("没有找到ID[" + id + "]的Article数据");
        }
        return article;
    }

    @Override
    public Article create(CreateArticleCommand command) {
        ArticleType articleType = articleTypeService.searchByID(command.getType());
        Account account = accountService.searchByID(command.getAccount());
        Article article = new Article(articleType, command.getTitle(), account, command.getContent(), command.getStatus());
        articleRepository.save(article);
        return article;
    }

    @Override
    public Article edit(EditArticleCommand command) {
        ArticleType articleType = articleTypeService.searchByID(command.getType());

        Article article = this.searchByID(command.getId());
        article.fainWhenConcurrencyViolation(command.getVersion());

        article.changeTitle(command.getTitle());
        article.changeContent(command.getContent());
        article.changeType(articleType);
        articleRepository.update(article);
        return article;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        Article article = this.searchByID(command.getId());
        article.fainWhenConcurrencyViolation(command.getVersion());

        if (article.getStatus() == EnableStatus.DISABLE) {
            article.changeStatus(EnableStatus.ENABLE);
        } else {
            article.changeStatus(EnableStatus.DISABLE);
        }
        articleRepository.update(article);
    }

    @Override
    public Pagination<Article> apiArticleList(ListArticleCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return articleRepository.pagination(command.getPage(),command.getPageSize(),criterionList,orderList);
    }
}
