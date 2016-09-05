package xinyongbang.application.groupchat.representation;

import xinyongbang.application.group.representation.GroupRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.enums.ChatType;
import xinyongbang.infrastructure.persistence.hibernate.group.GroupRepository;

import java.util.Date;

/**
 * Created by YJH on 2016/5/23.
 */
public class GroupChatRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private GroupRepresentation group;        //群
    private String content;     //内容
    private UserRepresentation user;          //发送人
    private ChatType chatType;          //聊天内容类型

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public GroupRepresentation getGroup() {
        return group;
    }

    public void setGroup(GroupRepresentation group) {
        this.group = group;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserRepresentation getUser() {
        return user;
    }

    public void setUser(UserRepresentation user) {
        this.user = user;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }
}
