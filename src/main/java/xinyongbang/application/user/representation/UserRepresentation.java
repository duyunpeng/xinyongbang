package xinyongbang.application.user.representation;

import xinyongbang.application.account.representation.AccountRepresentation;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.application.identitycard.representation.IdentityCardRepresentation;
import xinyongbang.core.enums.AuthStatus;
import xinyongbang.domain.model.clicklike.ClickLike;
import xinyongbang.domain.model.identitycard.IdentityCard;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
public class UserRepresentation extends AccountRepresentation {

    private String name;                //网名
    private AreaRepresentation area;                  //地区
    private IdentityCardRepresentation identityCard;  //身份证信息
    private BigDecimal gold;           //金币
    private AuthStatus authStatus;      //认证状态
    private String jobDescription;      //工作介绍
    private Double longitude;       //经度
    private Double latitude;        //维度
    private Integer credit;         //信用
    private Integer ranking;        //排行

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaRepresentation getArea() {
        return area;
    }

    public void setArea(AreaRepresentation area) {
        this.area = area;
    }

    public IdentityCardRepresentation getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(IdentityCardRepresentation identityCard) {
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
}
