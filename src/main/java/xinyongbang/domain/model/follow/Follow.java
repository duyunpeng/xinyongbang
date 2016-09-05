package xinyongbang.domain.model.follow;

import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.user.User;

import java.util.Date;

/**
 * Created by dw on 2016/6/12.
 */
public class Follow extends ConcurrencySafeEntity {

    private User user;                  //用户
    private User followUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFollowUser() {
        return followUser;
    }

    public void setFollowUser(User followUser) {
        this.followUser = followUser;
    }

    public Follow() {
        super();
    }

    public Follow(User user, User followUser) {
        this.user = user;
        this.followUser = followUser;
        this.setCreateDate(new Date());
    }
}
