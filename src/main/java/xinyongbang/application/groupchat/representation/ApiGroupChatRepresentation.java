package xinyongbang.application.groupchat.representation;

import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.core.enums.ChatType;

import java.util.Date;

/**
 * Created by YJH on 2016/5/23.
 */
public class ApiGroupChatRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String group;        //群
    private String groupName;        //群名称
    private String content;     //内容
    private String user;          //发送人
    private String userName;          //发送人账号
    private PictureRepresentation sendUserHead; //发送用户头像
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public PictureRepresentation getSendUserHead() {
        return sendUserHead;
    }

    public void setSendUserHead(PictureRepresentation sendUserHead) {
        this.sendUserHead = sendUserHead;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }
}
