package xinyongbang.application.friend.command;

/**
 * Created by YJH on 2016/5/16.
 */
public class AddFriendCommand {

    private String user;                  //用户
    private String friend;                //好友账号
    private String validationMessage;       //验证信息

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}
