package xinyongbang.application.album.command;

/**
 * Created by dw on 2016/5/19.
 */
public class CreateAlbumPictureCommand {

    private String albumId ;

    private String picPath;   //图片路劲

    private String describe ;   //描述

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }


    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
