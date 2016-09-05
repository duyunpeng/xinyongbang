package xinyongbang.domain.model.group;

import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.user.User;

import java.util.Date;
import java.util.List;

/**
 * 群
 * Created by YJH on 2016/5/13.
 */
public class Group extends ConcurrencySafeEntity {

    private String groupNo;     //群号
    private User user;        //创建用户
    private String name;            //名称
    private List<User> userList;    //用户列表
    private String description;     //群描述
    private Picture picture;        //群头像

    public void changeName(String name) {
        this.name = name;
    }

    public void changeUserList(List<User> userList) {
        this.userList = userList;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changePicture(Picture picture) {
        this.picture = picture;
    }

    private void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setUserList(List<User> userList) {
        this.userList = userList;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String getDescription() {
        return description;
    }

    public Picture getPicture() {
        return picture;
    }

    public Group() {
        super();
    }

    public Group(String groupNo, User user, String name, List<User> userList, String description, Picture picture) {
        this.groupNo = groupNo;
        this.user = user;
        this.name = name;
        this.userList = userList;
        this.description = description;
        this.picture = picture;
        this.setCreateDate(new Date());
    }
}
