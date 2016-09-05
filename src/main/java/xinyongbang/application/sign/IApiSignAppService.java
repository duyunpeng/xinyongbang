package xinyongbang.application.sign;

import xinyongbang.application.sign.command.SignCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/29.
 */
public interface IApiSignAppService {
    ApiResponse sign(SignCommand command);

    ApiResponse judgeSign(SignCommand command);
}
