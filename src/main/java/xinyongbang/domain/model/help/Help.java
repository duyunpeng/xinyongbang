package xinyongbang.domain.model.help;

import xinyongbang.core.enums.EnableStatus;
import xinyongbang.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Created by dw on 2016/5/23.
 */
public class Help extends ConcurrencySafeEntity {

    private String title;            //标题
    private String content;          //内容
    private EnableStatus status;     //状态

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeStatus(EnableStatus status) {
        this.status = status;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public EnableStatus getStatus() {
        return status;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private void setStatus(EnableStatus status) {
        this.status = status;
    }

    public Help(){
    }

    public Help(String title, String content, EnableStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.setCreateDate(new Date());
    }

}
