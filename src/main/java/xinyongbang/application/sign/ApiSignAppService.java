package xinyongbang.application.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.sign.command.SignCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.domain.service.sign.ISignService;

/**
 * Created by YJH on 2016/4/29.
 */
@Service("apiSignAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiSignAppService implements IApiSignAppService {

    @Autowired
    private ISignService signService;

    @Override
    public ApiResponse sign(SignCommand command) {
        signService.apiSign(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse judgeSign(SignCommand command) {
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), signService.apiJudgeSign(command));
    }
}
