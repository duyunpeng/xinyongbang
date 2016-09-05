package xinyongbang.core.enums;

/**
 * Created by YJH on 2016/5/20.
 */
public enum AppPushType {

    NEW_CHAT("新消息", 1, Boolean.FALSE),
    NEW_FRIEND("新朋友", 2, Boolean.FALSE),
    NEW_FRIEND_ANSWER("好友答复", 3, Boolean.FALSE),
    NEW_GROUP_CHAT("群新消息", 4, Boolean.FALSE),
    NEW_GROUP("加入群请求", 5, Boolean.FALSE),
    NEW_GROUP_ANSWER("答复加入群请求", 6, Boolean.FALSE);

    private AppPushType(String name, int value, Boolean onlyQuery) {
        this.name = name;
        this.value = value;
        this.onlyQuery = onlyQuery;
    }

    private String name;

    private int value;

    private Boolean onlyQuery;                  // 仅用于页面查询和业务逻辑无关

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Boolean isOnlyQuery() {
        return onlyQuery;
    }

}
