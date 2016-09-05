package xinyongbang.application.groupverify.command;

import java.util.List;

/**
 * Created by YJH on 2016/5/26.
 */
public class NewGroupVerifyCommand {

    private String user;
    private List<String> invitationUser;
    private String group;
    private String validationMessage;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getInvitationUser() {
        return invitationUser;
    }

    public void setInvitationUser(List<String> invitationUser) {
        this.invitationUser = invitationUser;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}
