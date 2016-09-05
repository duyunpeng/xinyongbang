package xinyongbang.application.identitycard.command;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by dyp on 2016/5/17.
 */
public class AttestationIdentityCardCommand {

    private String user;

    @NotBlank(message = "{identityCard.name.NotBlank.message}")
    private String name;        //身份证上面的姓名
    @NotBlank(message = "{identityCard.cardNumber.NotBlank.message}")
    private String cardNumber;      //身份证号码
    @NotNull(message = "{identityCard.frontPic.NotNull.messages}")
    private String frontPic;     //身份证正面照片
    @NotNull(message = "{identityCard.backPic.NotNull.messages}")
    private String backPic;        //身份证背面照片
    @NotNull(message = "{identityCard.handTakePic.NotNull.messages}")
    private String handTakePic;        //手持身份证照片

    public String getBackPic() {
        return backPic;
    }

    public void setBackPic(String backPic) {
        this.backPic = backPic;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFrontPic() {
        return frontPic;
    }

    public void setFrontPic(String frontPic) {
        this.frontPic = frontPic;
    }

    public String getHandTakePic() {
        return handTakePic;
    }

    public void setHandTakePic(String handTakePic) {
        this.handTakePic = handTakePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

