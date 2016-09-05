package xinyongbang.application.user.representation;

import xinyongbang.application.account.representation.ApiAccountRepresentation;
import xinyongbang.application.area.representation.ApiAreaRepresentation;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.core.enums.AuthStatus;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/5/18.
 */
public class ApiUserRepresentation extends ApiAccountRepresentation {

    private String name;                //网名
    private ApiAreaRepresentation area;                  //地区
    private String identityCard;  //身份证信息
    private BigDecimal gold;           //金币
    private AuthStatus authStatus;      //认证状态
    private String jobDescription;      //工作介绍
    private boolean clicked;            //是否点赞
    private Double longitude;       //经度
    private Double latitude;        //维度
    private Integer credit;         //信用
    private Integer ranking;        //排行
    private boolean follow;         //是否关注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApiAreaRepresentation getArea() {
        return area;
    }

    public void setArea(ApiAreaRepresentation area) {
        this.area = area;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }

    public AuthStatus getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }
}
