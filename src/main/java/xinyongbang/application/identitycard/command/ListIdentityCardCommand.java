package xinyongbang.application.identitycard.command;

import org.hibernate.validator.constraints.NotBlank;
import xinyongbang.core.common.BasicPaginationCommand;
import xinyongbang.domain.model.picture.Picture;

import javax.validation.constraints.NotNull;

/**
 * Created by dyp on 2016/4/20.
 */
public class ListIdentityCardCommand extends BasicPaginationCommand{
    @NotBlank(message = "{identityCard.cardNumber.NotBlank.message}")
    private String cardNumber;       //身份证号码
    @NotBlank(message = "{identityCard.name.NotBlank.message}")
    private String name;            //身份证上面的姓名
    @NotNull(message = "{identityCard.frontPic.NotNull.messages}")
    private Picture frontPic;       //身份证正面照片
    @NotNull(message = "{identityCard.backPic.NotNull.messages}")
    private Picture backPic;        //身份证背面照面

    public Picture getBackPic() {
        return backPic;
    }

    public void setBackPic(Picture backPic) {
        this.backPic = backPic;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Picture getFrontPic() {
        return frontPic;
    }

    public void setFrontPic(Picture frontPic) {
        this.frontPic = frontPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
