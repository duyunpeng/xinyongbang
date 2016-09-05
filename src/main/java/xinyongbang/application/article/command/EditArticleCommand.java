package xinyongbang.application.article.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.application.shared.command.SharedCommand;

/**
 * Created by YJH on 2016/4/19.
 */
public class EditArticleCommand extends SharedCommand {

    @NotBlank(message = "{article.type.NotBlank.message}")
    private String type;       //文章类型
    @NotBlank(message = "{article.title.NotBlank.message}")
    private String title;           //文章标题
    @NotBlank(message = "{article.content.NotBlank.message}")
    private String content;         //文章内容

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
