package xinyongbang.application.help.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.enums.EnableStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by dw on 2016/5/23.
 */
public class EditHelpCommand extends SharedCommand {

    @NotBlank(message = "{help.title.NoBlank.message}")
    private String updateTitle ;        //修改标题

    @NotBlank(message = "{help.content.NoBlank.message}")
    private String updateContent ;     //修改内容


    @NotNull(message = "{help.status.NotBlank.message}")
    private EnableStatus status;    //状态

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
}
