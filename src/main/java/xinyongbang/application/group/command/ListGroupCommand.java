package xinyongbang.application.group.command;

import xinyongbang.core.common.BasicPaginationCommand;

import java.util.List;

/**
 * Created by jm on 16-5-19.
 */
public class ListGroupCommand extends BasicPaginationCommand {

    private String user;

    private String group;

    private String groupNo;

    private List<String> userId;

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }
}
