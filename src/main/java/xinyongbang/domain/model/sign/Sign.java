package xinyongbang.domain.model.sign;

import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.user.User;

import java.util.Date;

/**
 * 签到
 * Created by YJH on 2016/4/15.
 */
public class Sign extends ConcurrencySafeEntity {

    private User user;      //签到用户

    public void changeUser(User user) {
        this.user = user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Sign() {
        super();
    }

    public Sign(User user) {
        this.user = user;
        this.setCreateDate(new Date());
    }
}
