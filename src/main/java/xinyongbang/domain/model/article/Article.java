package xinyongbang.domain.model.article;

import xinyongbang.core.enums.EnableStatus;
import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.account.Account;

import java.util.Date;

/**
 * 文章
 * Created by YJH on 2016/4/15.
 */
public class Article extends ConcurrencySafeEntity {

    private ArticleType type;       //文章类型
    private String title;           //文章标题
    private Account account;        //发布人
    private String content;         //文章内容
    private EnableStatus status;    //文章状态

    public void changeType(ArticleType type) {
        this.type = type;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeStatus(EnableStatus status) {
        this.status = status;
    }

    private void setType(ArticleType type) {
        this.type = type;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setAccount(Account account) {
        this.account = account;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private void setStatus(EnableStatus status) {
        this.status = status;
    }

    public ArticleType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Account getAccount() {
        return account;
    }

    public String getContent() {
        return content;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public Article() {
        super();
    }

    public Article(ArticleType type, String title, Account account, String content, EnableStatus status) {
        this.type = type;
        this.title = title;
        this.account = account;
        this.content = content;
        this.status = status;
        this.setCreateDate(new Date());
    }
}
