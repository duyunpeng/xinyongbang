package xinyongbang.domain.model.friend;

import xinyongbang.core.enums.VerifyStatus;
import xinyongbang.core.enums.YesOrNoStatus;
import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.user.User;

import java.util.Date;

/**
 * 好友
 * Created by YJH on 2016/4/22.
 */
public class Friend extends ConcurrencySafeEntity {

    private User user;                  //用户
    private User friend;                //好友
    private VerifyStatus verifyStatus;  //好友验证状态
    private String validationMessage;   //验证信息

    public void changeVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setFriend(User friend) {
        this.friend = friend;
    }

    private void setVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public User getUser() {
        return user;
    }

    public User getFriend() {
        return friend;
    }

    public VerifyStatus getVerifyStatus() {
        return verifyStatus;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public Friend() {
        super();
    }

    public Friend(User user, User friend, VerifyStatus verifyStatus, String validationMessage) {
        this.user = user;
        this.friend = friend;
        this.verifyStatus = verifyStatus;
        this.validationMessage = validationMessage;
        this.setCreateDate(new Date());
    }
}
