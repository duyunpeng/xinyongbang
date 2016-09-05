package xinyongbang.application.group.command;

/**
 * Created by YJH on 2016/5/24.
 */
public class NewGroupCommand {

    private String user;
    private String groupName;
    private String description;     //群描述
    private String picture;        //群头像

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
