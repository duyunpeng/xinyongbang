package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.friend.IApiFriendAppService;
import xinyongbang.application.friend.command.AddFriendCommand;
import xinyongbang.application.friend.command.AnswerCommand;
import xinyongbang.application.friend.command.ListFriendCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.exception.*;
import xinyongbang.core.util.CoreHttpUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/5/3.
 */
@Controller
@RequestMapping("/app/api/friend")
public class ApiAppFriendController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiFriendAppService apiFriendAppService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResponse friendList(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListFriendCommand command = this.authenticationAndConvert(verificationCommand, ListFriendCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiFriendAppService.friendList(command);
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
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }


    @RequestMapping(value = "/answer")
    @ResponseBody
    public ApiResponse answer(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            AnswerCommand command = this.authenticationAndConvert(verificationCommand, AnswerCommand.class);
            apiResponse = apiFriendAppService.answer(command);
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
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public ApiResponse addFriend(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            AddFriendCommand command = this.authenticationAndConvert(verificationCommand, AddFriendCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiFriendAppService.addFriend(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoLoginException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_LOGIN);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            if (e.getMessage().indexOf("等待好友答复") != -1) {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_WAIT_FRIEND_ANSWER);
            } else {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_EXIST_FRIEND);
            }
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_ACCOUNT);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public ApiResponse deleteFriend(ApiVerificationCommand verificationCommand, HttpSession session) {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListFriendCommand command = this.authenticationAndConvert(verificationCommand, ListFriendCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiFriendAppService.deleteFriend(command);
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
        } catch (ApiDataException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/wait_answer")
    @ResponseBody
    public ApiResponse waitAnswerList(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListFriendCommand command = this.authenticationAndConvert(verificationCommand, ListFriendCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiFriendAppService.waitAnswerList(command);
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
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

}
