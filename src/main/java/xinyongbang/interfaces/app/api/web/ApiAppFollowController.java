package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.follow.IApiFollowAppService;
import xinyongbang.application.follow.command.FollowCommand;
import xinyongbang.application.follow.command.ListFollowCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.exception.*;
import xinyongbang.core.util.CoreHttpUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by dw on 2016/6/12.
 */
@Controller
@RequestMapping("/app/api/follow")
public class ApiAppFollowController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiFollowAppService apiFollowAppService;


    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResponse list(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            this.authenticationAndConvert(verificationCommand);
            ListFollowCommand command = new ListFollowCommand();
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiFollowAppService.list(command);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public ApiResponse addFollow(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            FollowCommand command = this.authenticationAndConvert(verificationCommand, FollowCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiFollowAppService.addFollow(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_ACCOUNT);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_EXIST_FOLLOW);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/cancel")
    @ResponseBody
    public ApiResponse cancelFollow(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            FollowCommand command = this.authenticationAndConvert(verificationCommand, FollowCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiFollowAppService.cancelFollow(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_ACCOUNT);
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

}
