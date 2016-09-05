package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.account.IAccountAppService;
import xinyongbang.application.shared.command.SmsCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.common.Constants;
import xinyongbang.core.exception.ApiAuthenticationException;
import xinyongbang.core.exception.ApiUnknownException;
import xinyongbang.core.redis.RedisService;
import xinyongbang.core.sms.obj.SmsTemplate;
import xinyongbang.core.sms.service.SmsSender;
import xinyongbang.core.util.CoreStringUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/4/28.
 */
@Controller
@RequestMapping("/app/api/sms")
public class ApiAppSmsController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    @Autowired
    private IAccountAppService accountAppService;

    @Autowired
    private SmsSender smsSender;

    @RequestMapping(value = "/sms_register")
    @ResponseBody
    public ApiResponse registerSms(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            SmsCommand command = this.authenticationAndConvert(verificationCommand, SmsCommand.class);
            if (null == accountAppService.searchByAccountName(command.getPhone(), Constants.APP_KRY)) {
                if (redisService.exists(command.getPhone())) {
                    apiResponse = new ApiResponse(ApiReturnCode.ERROR_CODE_NOT_EXPIRED);
                } else {
                    String code = CoreStringUtils.randomNum(6);

                    //发送短信
                    smsSender.send(command.getPhone(), code, SmsTemplate.REGISTER);

                    redisService.addCache(command.getPhone(), code);
                    apiResponse = new ApiResponse(ApiReturnCode.SUCCESS);
                }
            } else {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_EXIST_ACCOUNT);
            }
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }


    @RequestMapping(value = "/sms_password")
    @ResponseBody
    public ApiResponse passwordSms(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            SmsCommand command = this.authenticationAndConvert(verificationCommand, SmsCommand.class);
            if (null != accountAppService.searchByAccountName(command.getPhone(), Constants.APP_KRY)) {
                if (redisService.exists(command.getPhone())) {
                    apiResponse = new ApiResponse(ApiReturnCode.ERROR_CODE_NOT_EXPIRED);
                } else {
                    String code = CoreStringUtils.randomNum(6);

                    //发送短信
                    smsSender.send(command.getPhone(), code, SmsTemplate.RESETPWD);

                    redisService.addCache(command.getPhone(), code);
                    apiResponse = new ApiResponse(ApiReturnCode.SUCCESS);
                }
            } else {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_ACCOUNT);
            }
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/sms_pay_password")
    @ResponseBody
    public ApiResponse payPasswordSms(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            SmsCommand command = this.authenticationAndConvert(verificationCommand, SmsCommand.class);
            if (null != accountAppService.searchByAccountName(command.getPhone(), Constants.APP_KRY)) {
                if (redisService.exists(command.getPhone())) {
                    apiResponse = new ApiResponse(ApiReturnCode.ERROR_CODE_NOT_EXPIRED);
                } else {
                    //TODO 发送支付密码短信

                    String code = CoreStringUtils.randomNum(6);
                    redisService.addCache(command.getPhone(), code);
                    apiResponse = new ApiResponse(ApiReturnCode.SUCCESS);
                }
            } else {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_ACCOUNT);
            }
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/verification_code")
    @ResponseBody
    public ApiResponse verificationCode(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            SmsCommand command = this.authenticationAndConvert(verificationCommand, SmsCommand.class);
            if (redisService.exists(command.getPhone())) {
                if (redisService.getCache(command.getPhone()).equals(command.getVerificationCode())) {
                    apiResponse = new ApiResponse(ApiReturnCode.SUCCESS);
                    session.setAttribute("verificationCode", "true");
                    redisService.delete(command.getPhone());
                } else {
                    apiResponse = new ApiResponse(ApiReturnCode.ERROR_CODE_NOT_EQ);
                }
            } else {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_CODE);
            }
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/sms_change_user_name")
    @ResponseBody
    public ApiResponse changeUserNameSms(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            SmsCommand command = this.authenticationAndConvert(verificationCommand, SmsCommand.class);
            if (null == accountAppService.searchByAccountName(command.getPhone(), Constants.APP_KRY)) {
                if (redisService.exists(command.getPhone())) {
                    apiResponse = new ApiResponse(ApiReturnCode.ERROR_CODE_NOT_EXPIRED);
                } else {
                    String code = CoreStringUtils.randomNum(6);

                    //发送短信
                    smsSender.send(command.getPhone(), code, SmsTemplate.CHANGE_USERNAME);

                    redisService.addCache(command.getPhone(), code);
                    apiResponse = new ApiResponse(ApiReturnCode.SUCCESS);
                }
            } else {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_EXIST_ACCOUNT);
            }
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }
}
