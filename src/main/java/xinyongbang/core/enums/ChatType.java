package xinyongbang.core.enums;

/**
 * Created by YJH on 2016/4/22.
 */
public enum  ChatType {

    ALL("全部",0,Boolean.TRUE),
    TEXT("文本", 1, Boolean.FALSE),
    IMG("图片", 2, Boolean.FALSE);

    private ChatType(String name, int value, Boolean onlyQuery) {
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
