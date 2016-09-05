package xinyongbang.application.friend.command;

import xinyongbang.core.enums.VerifyStatus;

/**
 * Created by YJH on 2016/5/16.
 */
public class AnswerCommand {

    private String id;
    private VerifyStatus verifyStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VerifyStatus getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
