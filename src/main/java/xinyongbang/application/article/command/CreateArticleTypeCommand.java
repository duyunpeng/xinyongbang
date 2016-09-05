package xinyongbang.application.article.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.core.enums.EnableStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by YJH on 2016/4/19.
 */
public class CreateArticleTypeCommand {

    @NotBlank(message = "{articleType.name.NotBlank.message}")
    private String name;        //分类名称
    @NotNull(message = "{articleType.name.NotNull.message}")
    private EnableStatus status;//分类状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
