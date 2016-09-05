package xinyongbang.application.friend.representation;

import xinyongbang.application.user.representation.ApiUserRepresentation;
import xinyongbang.core.enums.VerifyStatus;
import xinyongbang.core.enums.YesOrNoStatus;

import java.util.Date;

/**
 * Created by YJH on 2016/5/18.
 */
public class ApiFriendRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private ApiUserRepresentation user;                  //用户
    private ApiUserRepresentation friend;                //好友
    private YesOrNoStatus follow;       //是否关注
    private VerifyStatus verifyStatus;  //好友验证状态
    private String validationMessage;   //验证信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ApiUserRepresentation getUser() {
        return user;
    }

    public void setUser(ApiUserRepresentation user) {
        this.user = user;
    }

    public ApiUserRepresentation getFriend() {
        return friend;
    }

    public void setFriend(ApiUserRepresentation friend) {
        this.friend = friend;
    }

    public YesOrNoStatus getFollow() {
        return follow;
    }

    public void setFollow(YesOrNoStatus follow) {
        this.follow = follow;
    }

    public VerifyStatus getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}
