package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.account.command.ResetPasswordCommand;
import xinyongbang.application.account.representation.AccountRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.IApiUserAppService;
import xinyongbang.application.user.command.ChangeUserNameCommand;
import xinyongbang.application.user.command.EditUserCommand;
import xinyongbang.application.user.command.FlushLocationCommand;
import xinyongbang.application.user.command.ListUserCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.common.Constants;
import xinyongbang.core.exception.ApiAuthenticationException;
import xinyongbang.core.exception.ApiUnknownException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.exception.NoLoginException;
import xinyongbang.core.util.CoreHttpUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/5/3.
 */
@Controller
@RequestMapping("/app/api/user")
public class ApiAppUserController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiUserAppService apiUserAppService;


    @RequestMapping(value = "/index_list")
    @ResponseBody
    public ApiResponse indexList(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            AccountRepresentation account = (AccountRepresentation) session.getAttribute(Constants.SESSION_USER);
            ListUserCommand command = this.authenticationAndConvert(verificationCommand, ListUserCommand.class);
            if (null != account) {
                command.setUser(account.getId());
            }
            apiResponse = apiUserAppService.indexPagination(command);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/info")
    @ResponseBody
    public ApiResponse info(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            this.authenticationAndConvert(verificationCommand);
            SharedCommand command = new SharedCommand();
            command.setId(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiUserAppService.info(command);
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

    @RequestMapping(value = "/friend_info")
    @ResponseBody
    public ApiResponse friendInfo(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListUserCommand command = this.authenticationAndConvert(verificationCommand, ListUserCommand.class);
            apiResponse = apiUserAppService.friendInfo(command);
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiUnknownException e) {
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

    @RequestMapping(value = "/reset_pwd")
    @ResponseBody
    public ApiResponse resetPassword(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ResetPasswordCommand command = this.authenticationAndConvert(verificationCommand, ResetPasswordCommand.class);
            if (null == session.getAttribute("verificationCode") || !session.getAttribute("verificationCode").toString().equals("true")) {
                return new ApiResponse(ApiReturnCode.ERROR_NO_CODE);
            }
            apiResponse = apiUserAppService.resetPassword(command);
            session.removeAttribute("verificationCode");
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
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

    @RequestMapping(value = "/reset_pay_pwd")
    @ResponseBody
    public ApiResponse resetPayPassword(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ResetPasswordCommand command = this.authenticationAndConvert(verificationCommand, ResetPasswordCommand.class);
            if (null == session.getAttribute("verificationCode") || !session.getAttribute("verificationCode").toString().equals("true")) {
                return new ApiResponse(ApiReturnCode.ERROR_NO_CODE);
            }
            apiResponse = apiUserAppService.resetPayPassword(command);
            session.removeAttribute("verificationCode");
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
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

    @RequestMapping(value = "/update_head_pic")
    @ResponseBody
    public ApiResponse updateHeadPic(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            EditUserCommand command = this.authenticationAndConvert(verificationCommand, EditUserCommand.class);
            command.setId(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiUserAppService.updateHeadPic(command);
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

    @RequestMapping(value = "/update_info")
    @ResponseBody
    public ApiResponse updateInfo(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            EditUserCommand command = this.authenticationAndConvert(verificationCommand, EditUserCommand.class);
            command.setId(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiUserAppService.updateInfo(command);
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

    @RequestMapping("/flush_location")
    @ResponseBody
    public ApiResponse flushLocation(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            FlushLocationCommand command = this.authenticationAndConvert(verificationCommand, FlushLocationCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiUserAppService.flushLocation(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_DATA_NOT_FOUND);
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

    @RequestMapping(value = "/find_friend")
    @ResponseBody
    public ApiResponse findFriend(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListUserCommand command = this.authenticationAndConvert(verificationCommand, ListUserCommand.class);
            apiResponse = apiUserAppService.findFriend(command);
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

    @RequestMapping(value = "/contacts_list")
    @ResponseBody
    public ApiResponse contactsList(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListUserCommand command = this.authenticationAndConvert(verificationCommand, ListUserCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiUserAppService.contactsList(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/change_user_name")
    @ResponseBody
    public ApiResponse changeUserName(ApiVerificationCommand verificationCommand, HttpSession session) {
        long startTime = System.currentTimeMillis();
        if (!session.getAttribute("verificationCode").toString().equals("true")) {
            return new ApiResponse(ApiReturnCode.ERROR_NO_CODE);
        }
        ApiResponse apiResponse;
        try {
            ChangeUserNameCommand command = this.authenticationAndConvert(verificationCommand, ChangeUserNameCommand.class);
            command.setUser(CoreHttpUtils.getSessionAccount(session).getId());
            apiResponse = apiUserAppService.changeUserName(command);
            session.removeAttribute("verificationCode");
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

}
