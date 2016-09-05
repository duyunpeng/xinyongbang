package xinyongbang.application.groupverify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.groupverify.command.AuthGroupVerifyCommand;
import xinyongbang.application.groupverify.command.ListGroupVerifyCommand;
import xinyongbang.application.groupverify.command.NewGroupVerifyCommand;
import xinyongbang.application.groupverify.representation.ApiGroupVerifyRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.enums.AppPushType;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.groupverify.GroupVerify;
import xinyongbang.domain.service.groupverify.IGroupVerifyService;
import xinyongbang.listener.XXRunnable;
import xinyongbang.listener.command.Push;

import java.util.List;

/**
 * Created by YJH on 2016/5/25.
 */
@Service("apiGroupVerifyAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiGroupVerifyAppService implements IApiGroupVerifyAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IGroupVerifyService groupVerifyService;


    @Override
    public ApiResponse invitationUser(NewGroupVerifyCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroup())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群id(group)不能为空", null);
        }
        groupVerifyService.apiInvitationUser(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse joinGroup(NewGroupVerifyCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroup())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群id(group)不能为空", null);
        }
        GroupVerify groupVerify = groupVerifyService.apiJoinGroup(command);
        ApiGroupVerifyRepresentation data = mappingService.map(groupVerify, ApiGroupVerifyRepresentation.class, false);
        //推送
        if (XXRunnable.user_ip.containsKey(groupVerify.getGroup().getUser().getUserName())) {
            Push push = new Push(AppPushType.NEW_GROUP.getValue(), data);
            XXRunnable.send(groupVerify.getGroup().getUser().getUserName(), push);
        }
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse auth(AuthGroupVerifyCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroupVerifyId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群请求groupVerifyId不能为空", null);
        }
        if (null == command.getVerifyStatus()) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "回复结果verifyStatus不能为空", null);
        }
        GroupVerify groupVerify = groupVerifyService.apiAuth(command);
        ApiGroupVerifyRepresentation data = mappingService.map(groupVerify, ApiGroupVerifyRepresentation.class, false);
        //推送
        if (XXRunnable.user_ip.containsKey(groupVerify.getApplicantUser().getUserName())) {
            Push push = new Push(AppPushType.NEW_GROUP_ANSWER.getValue(), data);
            XXRunnable.send(groupVerify.getApplicantUser().getUserName(), push);
        }
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse authList(ListGroupVerifyCommand command) {
        List<ApiGroupVerifyRepresentation> data = mappingService.mapAsList(groupVerifyService.apiAuthList(command), ApiGroupVerifyRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }
}
