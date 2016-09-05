package xinyongbang.application.article.command;

import xinyongbang.core.common.BasicPaginationCommand;

/**
 * Created by YJH on 2016/4/19.
 */
public class ListArticleCommand extends BasicPaginationCommand {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
