package xinyongbang.domain.model.collection;

import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.chat.Chat;
import xinyongbang.domain.model.groupchat.GroupChat;
import xinyongbang.domain.model.user.User;

import java.util.Date;

/**
 * 收藏
 * Created by YJH on 2016/5/24.
 */
public class Collection extends ConcurrencySafeEntity {

    private User user;          //用户
    private String source;     //来源
    private Chat chat;          //聊天记录
    private GroupChat groupChat;//群聊天记录

    private void setUser(User user) {
        this.user = user;
    }

    private void setSource(String source) {
        this.source = source;
    }

    private void setChat(Chat chat) {
        this.chat = chat;
    }

    private void setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
    }

    public User getUser() {
        return user;
    }

    public String getSource() {
        return source;
    }

    public Chat getChat() {
        return chat;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public Collection() {
        super();
    }

    public Collection(User user, String source, Chat chat, GroupChat groupChat) {
        this.user = user;
        this.source = source;
        this.chat = chat;
        this.groupChat = groupChat;
        this.setCreateDate(new Date());
    }
}
