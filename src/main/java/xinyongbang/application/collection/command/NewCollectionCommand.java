package xinyongbang.application.collection.command;

/**
 * Created by YJH on 2016/5/24.
 */
public class NewCollectionCommand {

    private String user;
    private String source;
    private String chat;
    private String groupChat;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(String groupChat) {
        this.groupChat = groupChat;
    }
}
