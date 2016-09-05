package xinyongbang.domain.model.picture;


import xinyongbang.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Created by YJH on 2016/4/12.
 */
public class Picture extends ConcurrencySafeEntity {

    private String picPath;
    private String miniPicPath;
    private String mediumPicPath;

    private Double size;
    private String name;

    private String describes;           //描述

    private void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    private void setMiniPicPath(String miniPicPath) {
        this.miniPicPath = miniPicPath;
    }

    private void setMediumPicPath(String mediumPicPath) {
        this.mediumPicPath = mediumPicPath;
    }

    private void setSize(Double size) {
        this.size = size;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getMiniPicPath() {
        return miniPicPath;
    }

    public String getMediumPicPath() {
        return mediumPicPath;
    }

    public Double getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getDescribes() {
        return describes;
    }

    public Picture() {
        super();
    }

    public Picture(String picPath, String miniPicPath, String mediumPicPath, Double size, String name, String describes) {
        this.picPath = picPath;
        this.miniPicPath = miniPicPath;
        this.mediumPicPath = mediumPicPath;
        this.size = size;
        this.name = name;
        this.describes = describes;
        super.setCreateDate(new Date());
    }
}
