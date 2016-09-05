package xinyongbang.application.help.command;

/**
 * Created by dw on 2016/5/24.
 */
public class HelpCommand {

    private String titleId ;       //帮助ID

    private String title ;         //标题

    private String content ;       //内容


    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
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
