package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.article.IApiArticleAppService;
import xinyongbang.application.article.command.ListArticleCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.exception.ApiAuthenticationException;
import xinyongbang.core.exception.ApiUnknownException;

/**
 * Created by dyp on 2016/6/12.
 */
@Controller
@RequestMapping("/app/api/article")
public class ApiAppArticleController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private IApiArticleAppService apiArticleAppService;


    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResponse articleList(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListArticleCommand command = this.authenticationAndConvert(verificationCommand, ListArticleCommand.class);
            apiResponse = apiArticleAppService.articleList(command);
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
}
