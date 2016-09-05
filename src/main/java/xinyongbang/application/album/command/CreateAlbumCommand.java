package xinyongbang.application.album.command;

/**
 * Created by YJH on 2016/5/16.
 */
public class CreateAlbumCommand {

    private String user;                  //创建用户
    private String name;                //名称
    private String describes;           //描述

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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
}
