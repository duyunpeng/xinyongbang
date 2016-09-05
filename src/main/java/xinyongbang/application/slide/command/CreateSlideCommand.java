package xinyongbang.application.slide.command;


import javax.validation.constraints.NotNull;

/**
 * Created by dyp on 2016/5/20.
 */
public class CreateSlideCommand {

    @NotNull(message = "{slide.sort.NotNull.messages}")
    private Integer sort;   //排序

    @NotNull(message = "{slide.picture.NotNull.messages}")
    private String picture;    //关联图片

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
