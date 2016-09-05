package xinyongbang.application.friend.command;

import xinyongbang.core.common.BasicPaginationCommand;

/**
 * Created by YJH on 2016/5/3.
 */
public class ListFriendCommand extends BasicPaginationCommand {

    private String id;

    private String user;

    private String friendUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }
}


