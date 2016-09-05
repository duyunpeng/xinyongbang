package xinyongbang.application.chat.command;

import xinyongbang.core.enums.ChatType;

/**
 * Created by YJH on 2016/5/16.
 */
public class NewChatCommand {

    private String sendUser;            //发送用户
    private String receiveUser;         //接收用户
    private String content;             //内容
    private ChatType chatType;          //聊天内容类型

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }
}
