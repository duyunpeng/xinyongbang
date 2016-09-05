package xinyongbang.application.groupverify.representation;

import xinyongbang.core.enums.VerifyStatus;

import java.util.Date;

/**
 * Created by YJH on 2016/5/26.
 */
public class ApiGroupVerifyRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String applicantUser;     //请求人
    private String applicantUserName;     //请求人账号
    private String group;            //群
    private String groupName;            //群
    private VerifyStatus verifyStatus;  //验证状态

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

    public String getApplicantUser() {
        return applicantUser;
    }

    public void setApplicantUser(String applicantUser) {
        this.applicantUser = applicantUser;
    }

    public String getApplicantUserName() {
        return applicantUserName;
    }

    public void setApplicantUserName(String applicantUserName) {
        this.applicantUserName = applicantUserName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public VerifyStatus getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(VerifyStatus verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
