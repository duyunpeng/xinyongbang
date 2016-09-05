package xinyongbang.application.album.representation;

import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;

import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/5/16.
 */
public class AlbumRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private UserRepresentation user;                  //创建用户
    private String name;                //名称
    private String describes;           //描述
    private List<PictureRepresentation> pictureList;  //图片集合

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

    public UserRepresentation getUser() {
        return user;
    }

    public void setUser(UserRepresentation user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public List<PictureRepresentation> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<PictureRepresentation> pictureList) {
        this.pictureList = pictureList;
    }
}
