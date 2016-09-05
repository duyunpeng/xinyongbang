package xinyongbang.application.golddetailed.command;

import xinyongbang.core.common.BasicPaginationCommand;

/**
 * Created by YJH on 2016/4/19.
 */
public class ListGoldDetailedCommand extends BasicPaginationCommand {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
