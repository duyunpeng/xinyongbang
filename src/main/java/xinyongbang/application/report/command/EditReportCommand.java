package xinyongbang.application.report.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.enums.HandleStatus;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Created by lvdi on 2016/4/19.
 */
public class EditReportCommand  extends SharedCommand {

    @NotBlank(message = "{report.handleDate.NotBlank.message}")
    private String handleResult;                   //处理结果说明

    private Date handleDate;                        //处理时间

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }


}
