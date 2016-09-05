package xinyongbang.application.group.representation;

import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.user.representation.ApiUserRepresentation;
import xinyongbang.domain.model.picture.Picture;

import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/5/18.
 */
public class ApiGroupRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String groupNo;     //群号
    private String user;                  //用户
    private String userName;                  //用户
    private String name;            //名称
    private List<ApiUserRepresentation> userList;    //用户列表
    private String description;     //群描述
    private PictureRepresentation picture;        //群头像

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

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApiUserRepresentation> getUserList() {
        return userList;
    }

    public void setUserList(List<ApiUserRepresentation> userList) {
        this.userList = userList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PictureRepresentation getPicture() {
        return picture;
    }

    public void setPicture(PictureRepresentation picture) {
        this.picture = picture;
    }
}
