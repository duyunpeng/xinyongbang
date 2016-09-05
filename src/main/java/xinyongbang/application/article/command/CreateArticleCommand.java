package xinyongbang.application.article.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.core.enums.EnableStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by YJH on 2016/4/19.
 */
public class CreateArticleCommand {

    @NotBlank(message = "{article.type.NotBlank.message}")
    private String type;       //文章类型
    @NotBlank(message = "{article.title.NotBlank.message}")
    private String title;           //文章标题
    private String account;        //发布人
    @NotBlank(message = "{article.content.NotBlank.message}")
    private String content;         //文章内容
    @NotNull(message = "{article.status.NotNull.message}")
    private EnableStatus status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
