package xinyongbang.application.help.representation;

import xinyongbang.core.enums.EnableStatus;

import java.util.Date;

/**
 * Created by dw on 2016/5/23.
 */
public class HelpRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String title ;      //标题
    private String content ;    //内容
    private EnableStatus status;    //状态

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
