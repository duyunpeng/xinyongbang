package xinyongbang.domain.model.idcard;

import xinyongbang.core.id.ConcurrencySafeEntity;

import java.util.Date;

/**
 * Created by dyp on 2016/5/19.身份证验证保存domain
 */
public class IdCard extends ConcurrencySafeEntity {

    private String name;        //身份证姓名
    private String cardNumber;      //身份证号码

    private void setName(String name) {
        this.name = name;
    }

    private void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public IdCard() {
        super();
    }

    public IdCard(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.setCreateDate(new Date());
    }
}
