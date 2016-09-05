package xinyongbang.application.clicklike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.clicklike.command.ClickLikeCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.service.clicklike.IClickLickService;

/**
 * Created by YJH on 2016/4/29.
 */
@Service("apiClickLikeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiClickLikeAppService implements IApiClickLikeAppService {

    @Autowired
    private IClickLickService clickLickService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public ApiResponse clickLike(ClickLikeCommand command) {
        if (CoreStringUtils.isEmpty(command.getUser())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "被点赞用户id不能为空", null);
        }
        clickLickService.apiClickLike(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse cancelClickLike(ClickLikeCommand command) {
        if (CoreStringUtils.isEmpty(command.getUser())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "被点赞用户id不能为空", null);
        }
        clickLickService.apiCancelClickLike(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse getClickLikeCount(ClickLikeCommand command) {
        if (CoreStringUtils.isEmpty(command.getUser())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "查询用户id不能为空", null);
        }
        int count = clickLickService.list(command).size();
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), count);
    }
}
