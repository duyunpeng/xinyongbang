package xinyongbang.application.user.command;

/**
 * Created by yjh on 16-6-13.
 */
public class ChangeUserNameCommand {

    private String user;
    private String newUserName;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }
}
