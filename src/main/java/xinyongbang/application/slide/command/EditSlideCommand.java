package xinyongbang.application.slide.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.application.shared.command.SharedCommand;

import javax.validation.constraints.NotNull;

/**
 * Created by dyp on 2016/5/20.
 */
public class EditSlideCommand extends SharedCommand {
    @NotNull(message = "{slide.sort.NotNull.messages}")
    private Integer sort;   //排序

    @NotBlank(message = "{slide.picture.NotBlank.messages}")
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
