package xinyongbang.application.identitycard.command;

import xinyongbang.core.common.BasicPaginationCommand;

/**
 * Created by dyp on 2016/5/11.
 */
public class IdentityCardVerifyCommand extends BasicPaginationCommand {

    private String name;
    private String cardNumber;

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
