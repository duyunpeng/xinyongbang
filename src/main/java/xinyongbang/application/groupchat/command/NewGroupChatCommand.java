package xinyongbang.application.groupchat.command;

import xinyongbang.core.enums.ChatType;

/**
 * Created by YJH on 2016/5/23.
 */
public class NewGroupChatCommand {

    private String group;               //群
    private String sendUser;            //发送用户
    private String content;             //内容
    private ChatType chatType;          //聊天内容类型

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
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
