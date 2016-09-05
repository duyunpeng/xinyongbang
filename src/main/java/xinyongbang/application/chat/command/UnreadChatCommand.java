package xinyongbang.application.chat.command;

import xinyongbang.core.common.BasicPaginationCommand;
import xinyongbang.core.enums.YesOrNoStatus;

/**
 * Created by dyp on 2016/5/24.
 */
public class UnreadChatCommand extends BasicPaginationCommand{
    private String receiveUser;
    private YesOrNoStatus receiveStatus;   //是否接收

    public YesOrNoStatus getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(YesOrNoStatus receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }
}
