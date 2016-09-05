package xinyongbang.application.chat.representation;

import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.core.enums.ChatType;
import xinyongbang.core.enums.YesOrNoStatus;

import java.util.Date;

/**
 * Created by YJH on 2016/5/16.
 */
public class ApiChatRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String sendUser;              //发送用户
    private String sendUserName;              //发送用户
    private String sendName;                //发送人名字
    private PictureRepresentation sendUserHead; //发送用户头像
    private String receiveUser;           //接受用户
    private String receiveUserName;           //接受用户
    private String receiveName;             //接收人名字
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

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
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

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public PictureRepresentation getSendUserHead() {
        return sendUserHead;
    }

    public void setSendUserHead(PictureRepresentation sendUserHead) {
        this.sendUserHead = sendUserHead;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
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
