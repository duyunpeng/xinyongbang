package xinyongbang.application.identitycard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.identitycard.command.AttestationIdentityCardCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.service.identitycard.IIdentityCardService;


/**
 * Created by dyp on 2016/5/11.
 */
@Service("apiIdentityCardAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiIdentityCardAppService implements IApiIdentityCardAppService {

    @Autowired
    private IIdentityCardService identityCardService;


    @Override
    public ApiResponse attestation(AttestationIdentityCardCommand command) {
        if (CoreStringUtils.isEmpty(command.getName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "name字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getCardNumber())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "cardNumber字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getBackPic())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "backPic字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getFrontPic())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "frontPic字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getHandTakePic())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "handTakePic字段不能为空", null);
        }
        identityCardService.apiAttestation(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }
}
