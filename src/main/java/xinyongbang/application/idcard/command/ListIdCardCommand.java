package xinyongbang.application.idcard.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.core.common.BasicPaginationCommand;

/**
 * Created by dyp on 2016/5/23.
 */
public class ListIdCardCommand extends BasicPaginationCommand {
    @NotBlank(message = "{idCard.name.NotBlank.message}")
    private String name;        //身份证姓名
    @NotBlank(message = "{idCard.cardNumber.NotBlank.message}")
    private String cardNumber;      //身份证号码

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
