package xinyongbang.application.help.command;

import xinyongbang.core.common.BasicPaginationCommand;
import xinyongbang.core.enums.EnableStatus;

/**
 * Created by fengguo on 2016/5/23.
 */
public class ListHelpCommand extends BasicPaginationCommand {

    private String title ;

    private String content ;

    private EnableStatus status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
