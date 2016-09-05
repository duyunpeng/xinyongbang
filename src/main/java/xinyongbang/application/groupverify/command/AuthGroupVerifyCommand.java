package xinyongbang.application.groupverify.command;

import xinyongbang.core.enums.VerifyStatus;

/**
 * Created by YJH on 2016/5/26.
 */
public class AuthGroupVerifyCommand {

    private String user;
    private String groupVerifyId;
    private VerifyStatus verifyStatus;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroupVerifyId() {
        return groupVerifyId;
    }

    public void setGroupVerifyId(String groupVerifyId) {
        this.groupVerifyId = groupVerifyId;
    }

    public VerifyStatus getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
