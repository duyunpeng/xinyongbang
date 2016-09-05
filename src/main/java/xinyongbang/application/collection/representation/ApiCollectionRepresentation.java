package xinyongbang.application.collection.representation;

import xinyongbang.application.chat.representation.ApiChatRepresentation;
import xinyongbang.application.groupchat.representation.ApiGroupChatRepresentation;

import java.util.Date;

/**
 * Created by YJH on 2016/5/24.
 */
public class ApiCollectionRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String user;
    private String userName;
    private ApiChatRepresentation chat;
    private ApiGroupChatRepresentation groupChat;
    private String source;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ApiChatRepresentation getChat() {
        return chat;
    }

    public void setChat(ApiChatRepresentation chat) {
        this.chat = chat;
    }

    public ApiGroupChatRepresentation getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(ApiGroupChatRepresentation groupChat) {
        this.groupChat = groupChat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
