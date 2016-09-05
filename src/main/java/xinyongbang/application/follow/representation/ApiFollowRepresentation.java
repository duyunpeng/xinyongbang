package xinyongbang.application.follow.representation;

import xinyongbang.application.user.representation.ApiUserRepresentation;

import java.util.Date;

/**
 * Created by yjh on 16-6-13.
 */
public class ApiFollowRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private ApiUserRepresentation followUser;

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

    public ApiUserRepresentation getFollowUser() {
        return followUser;
    }

    public void setFollowUser(ApiUserRepresentation followUser) {
        this.followUser = followUser;
    }
}
