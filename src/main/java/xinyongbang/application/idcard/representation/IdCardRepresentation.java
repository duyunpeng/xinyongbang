package xinyongbang.application.idcard.representation;

import java.util.Date;

/**
 * Created by dyp on 2016/5/23.
 */
public class IdCardRepresentation {
    private String id;
    private Integer version;
    private Date createDate;

    private String name;        //身份证姓名
    private String cardNumber;      //身份证号码

    public IdCardRepresentation() {
        super();
    }

    public IdCardRepresentation(String cardNumber, Date createDate, String id, String name, Integer version) {
        this.cardNumber = cardNumber;
        this.createDate = createDate;
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
