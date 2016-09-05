package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.account.representation.AccountRepresentation;
import xinyongbang.application.feedback.IApiFeedBackAppService;
import xinyongbang.application.feedback.command.CreateFeedBackCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.common.Constants;
import xinyongbang.core.exception.ApiAuthenticationException;
import xinyongbang.core.exception.ApiUnknownException;

import javax.servlet.http.HttpSession;

/**
 * Created by dyp on 2016/5/18.
 */
@Controller
@RequestMapping("/app/api/feed_back")
public class ApiAppFeedBackController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private IApiFeedBackAppService apiFeedBackAppService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public ApiResponse create(ApiVerificationCommand verificationCommand, HttpSession session) {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            CreateFeedBackCommand command = this.authenticationAndConvert(verificationCommand, CreateFeedBackCommand.class);
            command.setPhone(null == session.getAttribute(Constants.SESSION_USER) ? null : ((AccountRepresentation) session.getAttribute(Constants.SESSION_USER)).getUserName());
            apiResponse = apiFeedBackAppService.apiCreate(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }
}
