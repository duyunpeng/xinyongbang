package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.groupverify.IApiGroupVerifyAppService;
import xinyongbang.application.groupverify.command.AuthGroupVerifyCommand;
import xinyongbang.application.groupverify.command.ListGroupVerifyCommand;
import xinyongbang.application.groupverify.command.NewGroupVerifyCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.exception.*;
import xinyongbang.core.util.CoreHttpUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/5/25.
 */
@Controller
@RequestMapping("/app/api/group_verify")
public class ApiAppGroupVerifyController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiGroupVerifyAppService apiGroupVerifyAppService;

    @RequestMapping(value = "/invitation_user")
    @ResponseBody
    public ApiResponse invitationUser(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            NewGroupVerifyCommand command = this.authenticationAndConvert(verificationCommand, NewGroupVerifyCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiGroupVerifyAppService.invitationUser(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (NotAdminException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NOT_GROUP_ADMIN);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_DATA_NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/join_group")
    @ResponseBody
    public ApiResponse joinGroup(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            NewGroupVerifyCommand command = this.authenticationAndConvert(verificationCommand, NewGroupVerifyCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiGroupVerifyAppService.joinGroup(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_DATA_NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/auth")
    @ResponseBody
    public ApiResponse auth(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            AuthGroupVerifyCommand command = this.authenticationAndConvert(verificationCommand, AuthGroupVerifyCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiGroupVerifyAppService.auth(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_DATA_NOT_FOUND);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/auth_list")
    @ResponseBody
    public ApiResponse authList(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListGroupVerifyCommand command = this.authenticationAndConvert(verificationCommand, ListGroupVerifyCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiGroupVerifyAppService.authList(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }
}
