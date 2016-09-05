package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.chat.IApiChatAppService;
import xinyongbang.application.chat.command.ListChatCommand;
import xinyongbang.application.chat.command.NewChatCommand;
import xinyongbang.application.chat.command.UnreadChatCommand;
import xinyongbang.application.chat.command.UpdateStatusCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.exception.ApiAuthenticationException;
import xinyongbang.core.exception.ApiUnknownException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.exception.NoLoginException;
import xinyongbang.core.util.CoreHttpUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/5/16.
 */
@Controller
@RequestMapping("/app/api/chat")
public class ApiAppChatController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiChatAppService apiChatAppService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResponse list(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListChatCommand command = this.authenticationAndConvert(verificationCommand, ListChatCommand.class);
            command.setSendUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiChatAppService.list(command);
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

    @RequestMapping(value = "/new")
    @ResponseBody
    public ApiResponse newChat(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            NewChatCommand command = this.authenticationAndConvert(verificationCommand, NewChatCommand.class);
            command.setSendUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiChatAppService.newChat(command);
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
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_ACCOUNT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/update_status")
    @ResponseBody
    public ApiResponse updateStatus(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            UpdateStatusCommand command = this.authenticationAndConvert(verificationCommand, UpdateStatusCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiChatAppService.updateStatus(command);
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
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_ACCOUNT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/unread")
    @ResponseBody
    public ApiResponse unread(ApiVerificationCommand verificationCommand, HttpSession session) {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            this.authenticationAndConvert(verificationCommand);
            UnreadChatCommand command = new UnreadChatCommand();
            command.setReceiveUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiChatAppService.unread(command);
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


