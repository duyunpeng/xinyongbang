package xinyongbang.domain.model.feedback;

import xinyongbang.core.enums.HandleStatus;
import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.picture.Picture;

import java.util.Date;
import java.util.List;

/**
 * 意见反馈
 * Created by YJH on 2016/4/15.
 */
public class FeedBack extends ConcurrencySafeEntity {

    private String phone;       //联系电话
    private String content;     //意见内容
    private HandleStatus status;//处理状态
    private List<Picture> pictureList;//添加图片

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeStatus(HandleStatus status) {
        this.status = status;
    }


    private void setContent(String content) {
        this.content = content;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private void setStatus(HandleStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public String getPhone() {
        return phone;
    }

    public HandleStatus getStatus() {
        return status;
    }

    public FeedBack() {
        super();
    }

    public FeedBack(String content, String phone, HandleStatus status, List<Picture> pictureList) {
        this.content = content;
        this.phone = phone;
        this.status = status;
        this.setCreateDate(new Date());
        this.pictureList = pictureList;
    }
}
