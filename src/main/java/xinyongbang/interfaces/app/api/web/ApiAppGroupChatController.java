package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.groupchat.command.ListGroupChatCommand;
import xinyongbang.application.groupchat.command.NewGroupChatCommand;
import xinyongbang.application.groupchat.IApiGroupChatAppService;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.exception.ApiAuthenticationException;
import xinyongbang.core.exception.ApiUnknownException;
import xinyongbang.core.exception.NoLoginException;
import xinyongbang.core.util.CoreHttpUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/5/23.
 */
@Controller
@RequestMapping("/app/api/group_chat")
public class ApiAppGroupChatController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiGroupChatAppService apiGroupChatAppService;

    @RequestMapping(value = "/new")
    @ResponseBody
    public ApiResponse addGroupChat(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            NewGroupChatCommand command = this.authenticationAndConvert(verificationCommand, NewGroupChatCommand.class);
            command.setSendUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiGroupChatAppService.addGroupChat(command);
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

    @RequestMapping(value = "/chat_list")
    @ResponseBody
    public ApiResponse chatList(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListGroupChatCommand command = this.authenticationAndConvert(verificationCommand, ListGroupChatCommand.class);
            apiResponse = apiGroupChatAppService.chatList(command);
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
