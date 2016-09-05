package xinyongbang.application.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.group.command.EditGroupCommand;
import xinyongbang.application.group.command.ListGroupCommand;
import xinyongbang.application.group.command.NewGroupCommand;
import xinyongbang.application.group.representation.ApiGroupRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.service.group.IGroupService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;
import xinyongbang.infrastructure.persistence.hibernate.groupchat.GroupChatRepository;

import java.util.List;

/**
 * Created by jm on 16-5-19.
 */
@Service("apiGroupAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiGroupAppService implements IApiGroupAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IGroupService groupService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse groupList(ListGroupCommand command) {
        List<ApiGroupRepresentation> data = mappingService.mapAsList(groupService.apiGroupList(command), ApiGroupRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse groupInfo(ListGroupCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroup())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群id(group)不能为空", null);
        }
        ApiGroupRepresentation group = mappingService.map(groupService.searchByID(command.getGroup()), ApiGroupRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), group);
    }

    @Override
    public ApiResponse newGroup(NewGroupCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroupName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群名称(groupName)不能为空", null);
        }
        groupService.apiCreate(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse findGroup(ListGroupCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroupNo())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群号(groupNo)不能为空", null);
        }
        List<ApiGroupRepresentation> data = mappingService.mapAsList(groupService.apiFindGroup(command), ApiGroupRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse deleteGroupUser(ListGroupCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroup())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群号(groupNo)不能为空", null);
        }
        groupService.apiDeleteGroupUser(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse promotion() {
        List<ApiGroupRepresentation> data = mappingService.mapAsList(groupService.apiPromotion(), ApiGroupRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse updateInfo(EditGroupCommand command) {
        groupService.apiUpdateInfo(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }
}
