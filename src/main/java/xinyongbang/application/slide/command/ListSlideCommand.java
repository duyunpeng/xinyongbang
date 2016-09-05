package xinyongbang.application.slide.command;

import xinyongbang.core.common.BasicPaginationCommand;
import xinyongbang.domain.model.picture.Picture;

/**
 * Created by dyp on 2016/5/20.
 */
public class ListSlideCommand extends BasicPaginationCommand {

    private Integer sort;   //排序
    private Picture picture;    //关联图片

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
