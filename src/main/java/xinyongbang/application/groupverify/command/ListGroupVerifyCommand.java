package xinyongbang.application.groupverify.command;

import xinyongbang.core.enums.VerifyStatus;

/**
 * Created by YJH on 2016/5/26.
 */
public class ListGroupVerifyCommand {

    private VerifyStatus verifyStatus;

    private String user;

    public VerifyStatus getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
