package xinyongbang.listener.command;

/**
 * Created by YJH on 2016/5/20.
 */
public class Push {

    private int type;
    private Object data;

    public Push(Integer type, Object data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
