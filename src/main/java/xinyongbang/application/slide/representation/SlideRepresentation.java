package xinyongbang.application.slide.representation;


import xinyongbang.application.picture.representation.PictureRepresentation;

import java.util.Date;

/**
 * Created by dyp on 2016/5/20.
 */
public class SlideRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private Integer sort;   //排序
    private PictureRepresentation picture;    //关联图片

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public PictureRepresentation getPicture() {
        return picture;
    }

    public void setPicture(PictureRepresentation picture) {
        this.picture = picture;
    }
}
