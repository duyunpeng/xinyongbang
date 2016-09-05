package xinyongbang.domain.model.groupverify;

import xinyongbang.core.enums.VerifyStatus;
import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.model.user.User;

import java.util.Date;

/**
 * 群验证
 * Created by YJH on 2016/5/25.
 */
public class GroupVerify extends ConcurrencySafeEntity {

    private User applicantUser;     //请求人
    private Group group;            //群
    private String validationMessage;//验证消息
    private VerifyStatus verifyStatus;  //验证状态

    public void changeVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    private void setApplicantUser(User applicantUser) {
        this.applicantUser = applicantUser;
    }

    private void setGroup(Group group) {
        this.group = group;
    }

    private void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    private void setVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public User getApplicantUser() {
        return applicantUser;
    }

    public Group getGroup() {
        return group;
    }

    public VerifyStatus getVerifyStatus() {
        return verifyStatus;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public GroupVerify() {
        super();
    }

    public GroupVerify(User applicantUser, Group group, String validationMessage, VerifyStatus verifyStatus) {
        this.applicantUser = applicantUser;
        this.group = group;
        this.validationMessage = validationMessage;
        this.verifyStatus = verifyStatus;
        this.setCreateDate(new Date());
    }
}
