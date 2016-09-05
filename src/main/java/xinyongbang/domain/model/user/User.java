package xinyongbang.domain.model.user;

import xinyongbang.core.enums.AuthStatus;
import xinyongbang.core.enums.EnableStatus;
import xinyongbang.domain.model.account.Account;
import xinyongbang.domain.model.appkey.AppKey;
import xinyongbang.domain.model.area.Area;
import xinyongbang.domain.model.clicklike.ClickLike;
import xinyongbang.domain.model.identitycard.IdentityCard;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.role.Role;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户
 * Created by YJH on 2016/4/15.
 */
public class User extends Account {

    private String name;                //网名
    private Area area;                  //地区
    private IdentityCard identityCard;  //身份证信息
    private BigDecimal gold;           //金币
    private AuthStatus authStatus;      //认证状态
    private String payPassword;
    private String jobDescription;      //工作介绍
    private Double longitude;       //经度
    private Double latitude;        //维度
    private Integer credit;         //信用
    private Integer ranking;        //排行

    public void changeLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void changeLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void changeCredit(Integer credit) {
        this.credit = credit;
    }

    public void changeRanking(Integer ranking) {
        this.ranking = ranking;
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

    public void changeName(String name) {
        this.name = name;
    }

    public void changeArea(Area area) {
        this.area = area;
    }

    public void changeIdentityCard(IdentityCard identityCard) {
        this.identityCard = identityCard;
    }

    public void changeGold(BigDecimal gold) {
        this.gold = gold;
    }

    public void changeAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
    }

    public void changePayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public void changeJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setArea(Area area) {
        this.area = area;
    }

    private void setIdentityCard(IdentityCard identityCard) {
        this.identityCard = identityCard;
    }

    private void setGold(BigDecimal gold) {
        this.gold = gold;
    }

    private void setAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
    }

    private void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    private void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    private void setCredit(Integer credit) {
        this.credit = credit;
    }

    private void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public Area getArea() {
        return area;
    }

    public IdentityCard getIdentityCard() {
        return identityCard;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public AuthStatus getAuthStatus() {
        return authStatus;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public Integer getCredit() {
        return credit;
    }

    public Integer getRanking() {
        return ranking;
    }

    public User() {
        super();
    }

    public User(String userName, String password, String salt, List<Role> roles, AppKey appKey, Picture picture) {
        super(userName, password, salt, null, null, null, roles, null, appKey, EnableStatus.ENABLE, picture);
        this.gold = new BigDecimal(0);
        this.ranking = 0;
        this.authStatus = AuthStatus.NOT;
        this.setCreateDate(new Date());
    }

    public User(String name, Area area, IdentityCard identityCard, BigDecimal gold, AuthStatus authStatus, String payPassword, String jobDescription) {
        this.name = name;
        this.area = area;
        this.identityCard = identityCard;
        this.gold = gold;
        this.authStatus = authStatus;
        this.payPassword = payPassword;
        this.jobDescription = jobDescription;
        this.credit = 0;
        this.ranking = 0;
        this.setCreateDate(new Date());
    }

    public User(String userName, String password, String salt, String lastLoginIP, Date lastLoginDate, String lastLoginPlatform, List<Role> roles, String email, AppKey appKey, EnableStatus status, Picture headPic, String name, Area area, IdentityCard identityCard, BigDecimal gold, AuthStatus authStatus, String payPassword, String jobDescription) {
        super(userName, password, salt, lastLoginIP, lastLoginDate, lastLoginPlatform, roles, email, appKey, status, headPic);
        this.name = name;
        this.area = area;
        this.identityCard = identityCard;
        this.gold = gold;
        this.authStatus = authStatus;
        this.payPassword = payPassword;
        this.jobDescription = jobDescription;
        this.credit = 0;
        this.ranking = 0;
        this.setCreateDate(new Date());
    }
}
