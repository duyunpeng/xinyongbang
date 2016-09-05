package xinyongbang.application.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.follow.command.FollowCommand;
import xinyongbang.application.follow.command.ListFollowCommand;
import xinyongbang.application.follow.representation.ApiFollowRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.follow.Follow;
import xinyongbang.domain.service.follow.IFollowService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dw on 2016/6/12.
 */
@Service("apiFollowAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiFollowAppService implements IApiFollowAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IFollowService followService;

    @Override
    public ApiResponse addFollow(FollowCommand command) {
        if (CoreStringUtils.isEmpty(command.getFollowUser())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "关注账号(followUser)不能为空", null);
        }
        followService.apiAddFollow(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse cancelFollow(FollowCommand command) {
        if (CoreStringUtils.isEmpty(command.getFollowUser())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "关注账号(followUser)不能为空", null);
        }
        followService.apiCancelFollow(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse list(ListFollowCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Follow> pagination = followService.apiList(command);
        List<ApiFollowRepresentation> data = mappingService.mapAsList(pagination.getData(), ApiFollowRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(),
                new Pagination<ApiFollowRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()));
    }
}
