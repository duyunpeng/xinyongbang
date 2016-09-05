package xinyongbang.domain.model.article;

import xinyongbang.core.enums.EnableStatus;
import xinyongbang.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * 文章分类
 * Created by YJH on 2016/4/15.
 */
public class ArticleType extends ConcurrencySafeEntity {

    private String name;
    private EnableStatus status;

    public void changeName(String name) {
        this.name = name;
    }

    public void changeStatus(EnableStatus status) {
        this.status = status;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public ArticleType() {
        super();
    }

    public ArticleType(String name, EnableStatus status) {
        this.name = name;
        this.status = status;
        this.setCreateDate(new Date());
    }
}
