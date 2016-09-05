package xinyongbang.application.album.command;

/**
 * Created by dw on 2016/5/19.
 */
public class EditAlbumPictureCommand {

    private String albumId ;

    private String picPath;   //图片路劲


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
}
