package xinyongbang.application.follow.command;

import xinyongbang.core.common.BasicPaginationCommand;

/**
 * Created by yjh on 16-6-13.
 */
public class ListFollowCommand extends BasicPaginationCommand {

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
