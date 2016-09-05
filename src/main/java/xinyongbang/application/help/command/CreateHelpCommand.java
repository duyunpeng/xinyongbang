package xinyongbang.application.help.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.core.enums.EnableStatus;

/**
 * Created by dw on 2016/5/23.
 */
public class CreateHelpCommand {

    @NotBlank(message = "{help.title.NoBlank.message}")
    private String title ;       //标题

    @NotBlank(message = "{help.content.NoBlank.message}")
    private String content ;     //内容

    private EnableStatus status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
