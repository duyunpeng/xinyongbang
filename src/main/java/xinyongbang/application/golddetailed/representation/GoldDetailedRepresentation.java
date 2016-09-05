package xinyongbang.application.golddetailed.representation;

import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.enums.FlowType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/4/19.
 */
public class GoldDetailedRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private UserRepresentation user;              //用户
    private BigDecimal goldNumber;  //金币数
    private FlowType flowType;      //方式
    private String description;     //说明

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

    public UserRepresentation getUser() {
        return user;
    }

    public void setUser(UserRepresentation user) {
        this.user = user;
    }

    public BigDecimal getGoldNumber() {
        return goldNumber;
    }

    public void setGoldNumber(BigDecimal goldNumber) {
        this.goldNumber = goldNumber;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
