package xinyongbang.domain.model.chat;

import xinyongbang.core.enums.ChatType;
import xinyongbang.core.enums.YesOrNoStatus;
import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.user.User;

import java.util.Date;

/**
 * Created by YJH on 2016/4/22.
 */
public class Chat extends ConcurrencySafeEntity {

    private User sendUser;              //发送用户
    private User receiveUser;           //接受用户
    private YesOrNoStatus receiveStatus;   //是否接收
    private String content;             //内容
    private ChatType chatType;          //聊天内容类型

    public void changeReceiveStatus(YesOrNoStatus receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    private void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    private void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }

    private void setReceiveStatus(YesOrNoStatus receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public User getSendUser() {
        return sendUser;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public YesOrNoStatus getReceiveStatus() {
        return receiveStatus;
    }

    public String getContent() {
        return content;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public Chat() {
        super();
    }

    public Chat(User sendUser, User receiveUser, YesOrNoStatus receiveStatus, String content, ChatType chatType) {
        this.sendUser = sendUser;
        this.receiveUser = receiveUser;
        this.receiveStatus = receiveStatus;
        this.content = content;
        this.chatType = chatType;
        this.setCreateDate(new Date());
    }
}
