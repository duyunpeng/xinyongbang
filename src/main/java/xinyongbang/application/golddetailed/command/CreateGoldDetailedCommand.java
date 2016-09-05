package xinyongbang.application.golddetailed.command;

import xinyongbang.core.enums.FlowType;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/4/19.
 */
public class CreateGoldDetailedCommand {

    private String user;              //用户
    private BigDecimal goldNumber;  //金币数
    private FlowType flowType;      //方式
    private String description;     //说明

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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
