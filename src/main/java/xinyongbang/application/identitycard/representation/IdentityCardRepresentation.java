package xinyongbang.application.identitycard.representation;

import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.domain.model.picture.Picture;

import java.util.Date;

/**
 * Created by dyp on 2016/4/20.
 */
public class IdentityCardRepresentation {
    private String id;
    private Integer version;
    private Date createDate;

    private String cardNumber;       //身份证号码
    private String name;            //身份证上面的姓名
    private PictureRepresentation frontPic;       //身份证正面照片
    private PictureRepresentation backPic;        //身份证背面照面
    private Picture handTakePic;    //手持身份证照片

    public PictureRepresentation getBackPic() {
        return backPic;
    }

    public void setBackPic(PictureRepresentation backPic) {
        this.backPic = backPic;
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

    public PictureRepresentation getFrontPic() {
        return frontPic;
    }

    public void setFrontPic(PictureRepresentation frontPic) {
        this.frontPic = frontPic;
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

    public Picture getHandTakePic() {
        return handTakePic;
    }

    public void setHandTakePic(Picture handTakePic) {
        this.handTakePic = handTakePic;
    }
}
