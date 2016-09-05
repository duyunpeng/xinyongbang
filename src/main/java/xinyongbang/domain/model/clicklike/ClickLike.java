package xinyongbang.domain.model.clicklike;

import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.user.User;

import java.util.Date;

/**
 * 点赞
 * Created by YJH on 2016/4/15.
 */
public class ClickLike extends ConcurrencySafeEntity {

    private User user;          //被点赞

    private User clickUser;     //点赞用户

    private void setUser(User user) {
        this.user = user;
    }

    private void setClickUser(User clickUser) {
        this.clickUser = clickUser;
    }

    public User getUser() {
        return user;
    }

    public User getClickUser() {
        return clickUser;
    }


    public ClickLike() {
        super();
    }

    public ClickLike(User user, User clickUser) {
        this.user = user;
        this.clickUser = clickUser;
        this.setCreateDate(new Date());
    }
}
