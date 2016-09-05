package xinyongbang.application.article.command;

import xinyongbang.core.common.BasicPaginationCommand;
import xinyongbang.core.enums.EnableStatus;

/**
 * Created by YJH on 2016/4/19.
 */
public class ListArticleTypeCommand extends BasicPaginationCommand {

    private String name;
    private EnableStatus status;

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
