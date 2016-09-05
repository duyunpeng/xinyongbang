package xinyongbang.application.chat.command;

import xinyongbang.core.common.BasicPaginationCommand;
import xinyongbang.core.enums.YesOrNoStatus;

/**
 * Created by YJH on 2016/5/16.
 */
public class ListChatCommand extends BasicPaginationCommand {

    private String sendUser;
    private String receiveUser;
    private YesOrNoStatus receiveStatus;   //是否接收

    public ListChatCommand() {

    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public YesOrNoStatus getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(YesOrNoStatus receiveStatus) {
        this.receiveStatus = receiveStatus;
    }
}
