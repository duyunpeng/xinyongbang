package xinyongbang.application.feedback.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.core.enums.HandleStatus;
import xinyongbang.domain.model.picture.Picture;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by dyp on 2016/4/19.
 */
public class CreateFeedBackCommand {
    @NotBlank(message = "{feedback.phone.NotBlank.message}")
    private String phone;       //联系电话
    @NotBlank(message = "{feedback.content.NotBlank.message}")
    private String content;     //意见内容
    @NotNull(message = "{feedback.status.NotNull.messages}")
    private HandleStatus status;//处理状态


    private List<String> pictureList;

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public String getPhone() {return phone;}

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HandleStatus getStatus() {
        return status;
    }

    public void setStatus(HandleStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
