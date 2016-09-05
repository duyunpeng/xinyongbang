package xinyongbang.application.follow.command;

/**
 * Created by YJH on 2016/5/16.
 */
public class FollowCommand {

    private String user;                  //用户
    private String followUser;                //好友账号

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFollowUser() {
        return followUser;
    }

    public void setFollowUser(String followUser) {
        this.followUser = followUser;
    }
}
