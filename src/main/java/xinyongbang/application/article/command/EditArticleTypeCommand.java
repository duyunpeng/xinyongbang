package xinyongbang.application.article.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.application.shared.command.SharedCommand;

/**
 * Created by YJH on 2016/4/19.
 */
public class EditArticleTypeCommand extends SharedCommand {

    @NotBlank(message = "{articleType.name.NotBlank.message}")
    private String name;    //文章分类名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
