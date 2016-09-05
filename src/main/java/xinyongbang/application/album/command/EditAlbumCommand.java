package xinyongbang.application.album.command;

import xinyongbang.application.shared.command.SharedCommand;

/**
 * Created by YJH on 2016/5/16.
 */
public class EditAlbumCommand extends SharedCommand {

    private String name;
    private String describes;

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
