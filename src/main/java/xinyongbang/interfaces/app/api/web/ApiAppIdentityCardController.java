package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.identitycard.IApiIdentityCardAppService;
import xinyongbang.application.identitycard.command.AttestationIdentityCardCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.exception.*;
import xinyongbang.core.util.CoreHttpUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by dyp on 2016/5/11.
 */
@Controller
@RequestMapping("/app/api/identity_card")
public class ApiAppIdentityCardController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiIdentityCardAppService apiIdentityCardAppService;

    @RequestMapping(value = "/attestation")
    @ResponseBody
    public ApiResponse attestation(ApiVerificationCommand verificationCommand, HttpSession session) {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            AttestationIdentityCardCommand command = this.authenticationAndConvert(verificationCommand, AttestationIdentityCardCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiIdentityCardAppService.attestation(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (IdentityCardVerifyNotEqualException e) {
            logger.info(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_ID_CARD_NOT_EQ);
        } catch (IdentityCardVerifyException e) {
            logger.info(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_ID_CARD_FAILURE);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_ID_CARD_REPEAT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.FAILURE);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }
}
