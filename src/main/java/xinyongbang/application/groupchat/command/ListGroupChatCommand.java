package xinyongbang.application.groupchat.command;

import xinyongbang.core.common.BasicPaginationCommand;

/**
 * Created by YJH on 2016/5/23.
 */
public class ListGroupChatCommand extends BasicPaginationCommand {

    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
