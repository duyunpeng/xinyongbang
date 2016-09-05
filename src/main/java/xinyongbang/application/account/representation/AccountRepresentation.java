package xinyongbang.application.account.representation;


import xinyongbang.application.appkey.representation.AppKeyRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.role.representation.RoleRepresentation;
import xinyongbang.core.enums.EnableStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/30 0030.
 */
public class AccountRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String userName;        //用户名
    private String password;        //密码
    private String salt;            //密码加密盐
    private String lastLoginIP;     //最后登录ip
    private Date lastLoginDate;     //最后登录时间
    private String lastLoginPlatform;//最后登录平台
    private List<RoleRepresentation> roles;               //用户角色
    private AppKeyRepresentation appKey;          //应用标识
    private EnableStatus status;     //状态
    private PictureRepresentation headPic;  //头像
    private String email;   //邮箱

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginPlatform() {
        return lastLoginPlatform;
    }

    public void setLastLoginPlatform(String lastLoginPlatform) {
        this.lastLoginPlatform = lastLoginPlatform;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<RoleRepresentation> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleRepresentation> roles) {
        this.roles = roles;
    }

    public AppKeyRepresentation getAppKey() {
        return appKey;
    }

    public void setAppKey(AppKeyRepresentation appKey) {
        this.appKey = appKey;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public PictureRepresentation getHeadPic() {
        return headPic;
    }

    public void setHeadPic(PictureRepresentation headPic) {
        this.headPic = headPic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
