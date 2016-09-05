package xinyongbang.domain.model.identitycard;

import xinyongbang.core.id.ConcurrencySafeEntity;
import xinyongbang.domain.model.picture.Picture;

import java.util.Date;

/**
 * 身份证信息
 * Created by YJH on 2016/4/15.
 */
public class IdentityCard extends ConcurrencySafeEntity {

    private String cardNumber;       //身份证号码
    private String name;            //身份证上面的姓名
    private Picture frontPic;       //身份证正面照片
    private Picture backPic;        //身份证背面照面
    private Picture handTakePic;    //手持身份证照片

    private void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setFrontPic(Picture frontPic) {
        this.frontPic = frontPic;
    }

    private void setBackPic(Picture backPic) {
        this.backPic = backPic;
    }

    public void setHandTakePic(Picture handTakePic) {
        this.handTakePic = handTakePic;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public Picture getFrontPic() {
        return frontPic;
    }

    public Picture getBackPic() {
        return backPic;
    }

    public Picture getHandTakePic() {
        return handTakePic;
    }

    public IdentityCard() {
        super();
    }

    public IdentityCard(String cardNumber, String name, Picture frontPic, Picture backPic, Picture handTakePic) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.frontPic = frontPic;
        this.backPic = backPic;
        this.handTakePic = handTakePic;
        this.setCreateDate(new Date());
    }
}
