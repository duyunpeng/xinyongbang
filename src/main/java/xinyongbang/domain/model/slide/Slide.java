package xinyongbang.domain.model.slide;

import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.picture.Picture;

import java.util.Date;

/**
 * 幻灯片表
 * Created by YJH on 2016/4/15.
 */
public class Slide extends ConcurrencySafeEntity {

    private Integer sort;   //排序
    private Picture picture;    //关联图片

    public void changeSort(Integer sort) {
        this.sort = sort;
    }

    public void changePicture(Picture picture) {
        this.picture = picture;
    }

    private void setSort(Integer sort) {
        this.sort = sort;
    }

    private void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Integer getSort() {
        return sort;
    }

    public Picture getPicture() {
        return picture;
    }

    public Slide() {
        super();
    }

    public Slide(Integer sort, Picture picture) {
        this.sort = sort;
        this.picture = picture;
        this.setCreateDate(new Date());
    }
}
