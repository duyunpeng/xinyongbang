package xinyongbang.application.chat.representation;

import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.enums.ChatType;
import xinyongbang.core.enums.YesOrNoStatus;

import java.util.Date;

/**
 * Created by YJH on 2016/4/22.
 */
public class ChatRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private UserRepresentation sendUser;              //发送用户
    private UserRepresentation receiveUser;           //接受用户
    private YesOrNoStatus receiveStatus;   //是否接收
    private String content;             //内容
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

    public UserRepresentation getSendUser() {
        return sendUser;
    }

    public void setSendUser(UserRepresentation sendUser) {
        this.sendUser = sendUser;
    }

    public UserRepresentation getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(UserRepresentation receiveUser) {
        this.receiveUser = receiveUser;
    }

    public YesOrNoStatus getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(YesOrNoStatus receiveStatus) {
        this.receiveStatus = receiveStatus;
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
