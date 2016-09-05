package xinyongbang.application.collection.command;

import xinyongbang.core.common.BasicPaginationCommand;

/**
 * Created by YJH on 2016/5/24.
 */
public class ListCollectionCommand extends BasicPaginationCommand {

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
