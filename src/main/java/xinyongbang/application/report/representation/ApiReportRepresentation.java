package xinyongbang.application.report.representation;

import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.core.enums.HandleStatus;
import xinyongbang.domain.model.picture.Picture;

import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/5/18.
 */
public class ApiReportRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String reportUser;                        //举报人
    private String reportUserName;                        //举报人账号
    private String quiltReportUser;                         //被举报人
    private String quiltReportUserName;                   //被举报人账号
    private String title;                           //举报标题
    private String content;                         //举报内容
    private List<PictureRepresentation> pictures;                 //图片
    private HandleStatus handleStatus;                    //处理状态
    private String handleResult;                   //处理结果说明
    private Date handleDate;                        //处理时间

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

    public String getReportUser() {
        return reportUser;
    }

    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public String getQuiltReportUser() {
        return quiltReportUser;
    }

    public void setQuiltReportUser(String quiltReportUser) {
        this.quiltReportUser = quiltReportUser;
    }

    public String getQuiltReportUserName() {
        return quiltReportUserName;
    }

    public void setQuiltReportUserName(String quiltReportUserName) {
        this.quiltReportUserName = quiltReportUserName;
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

    public List<PictureRepresentation> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureRepresentation> pictures) {
        this.pictures = pictures;
    }

    public HandleStatus getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(HandleStatus handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }
}
