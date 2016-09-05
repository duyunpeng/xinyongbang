package xinyongbang.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xinyongbang.application.slide.IApiSlideAppService;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.api.command.ApiVerificationCommand;
import xinyongbang.core.api.controller.BaseApiController;
import xinyongbang.core.exception.ApiAuthenticationException;

/**
 * Created by dyp on 2016/5/23.
 */
@Controller
@RequestMapping("/app/api/slide")
public class ApiAppSlideController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiSlideAppService apiSlideAppService;


    @RequestMapping(value = "/info")
    @ResponseBody
    public ApiResponse info(ApiVerificationCommand verificationCommand) {
        Long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            this.authenticationAndConvert(verificationCommand);
            apiResponse = apiSlideAppService.info();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_NO_CODE);
        }
        apiResponse.setDebugTime(System.currentTimeMillis()-startTime);
        return apiResponse;
    }
}
