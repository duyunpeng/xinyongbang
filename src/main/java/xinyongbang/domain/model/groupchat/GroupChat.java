package xinyongbang.domain.model.groupchat;

import xinyongbang.core.enums.ChatType;
import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.model.user.User;

import java.util.Date;

/**
 * Created by YJH on 2016/5/16.
 */
public class GroupChat extends ConcurrencySafeEntity {

    private Group group;        //群
    private String content;     //内容
    private User user;          //发送人
    private ChatType chatType;          //聊天内容类型

    private void setGroup(Group group) {
        this.group = group;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public Group getGroup() {
        return group;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public GroupChat() {
        super();
    }

    public GroupChat(Group group, String content, User user, ChatType chatType) {
        this.group = group;
        this.content = content;
        this.user = user;
        this.chatType = chatType;
        this.setCreateDate(new Date());
    }
}
