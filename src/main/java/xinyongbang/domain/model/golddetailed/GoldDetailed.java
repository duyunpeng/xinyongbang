package xinyongbang.domain.model.golddetailed;

import xinyongbang.core.enums.FlowType;
import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.user.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 金币明细表
 * Created by YJH on 2016/4/15.
 */
public class GoldDetailed extends ConcurrencySafeEntity {

    private User user;              //用户
    private BigDecimal goldNumber;  //金币数
    private FlowType flowType;      //方式
    private String description;     //说明

    public void changeUser(User user) {
        this.user = user;
    }

    public void changeGoldNumber(BigDecimal goldNumber) {
        this.goldNumber = goldNumber;
    }

    public void chnageFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setGoldNumber(BigDecimal goldNumber) {
        this.goldNumber = goldNumber;
    }

    private void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getGoldNumber() {
        return goldNumber;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public String getDescription() {
        return description;
    }

    public GoldDetailed() {
        super();
    }

    public GoldDetailed(User user, BigDecimal goldNumber, FlowType flowType, String description) {
        this.user = user;
        this.goldNumber = goldNumber;
        this.flowType = flowType;
        this.description = description;
        this.setCreateDate(new Date());
    }
}
