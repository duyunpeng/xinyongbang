package xinyongbang.domain.model.album;

import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.user.User;

import java.util.Date;
import java.util.List;

/**
 * 相册
 * Created by YJH on 2016/5/13.
 */
public class Album extends ConcurrencySafeEntity {

    private User user;                  //创建用户
    private String name;                //名称
    private String describes;           //描述
    private List<Picture> pictureList;  //图片集合

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescribes(String describes) {
        this.describes = describes;
    }

    public void changePictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescribes(String describes) {
        this.describes = describes;
    }

    private void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getDescribes() {
        return describes;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public Album() {
        super();
    }

    public Album(User user, String name, String describes, List<Picture> pictureList) {
        this.user = user;
        this.name = name;
        this.describes = describes;
        this.pictureList = pictureList;
        this.setCreateDate(new Date());
    }
}
