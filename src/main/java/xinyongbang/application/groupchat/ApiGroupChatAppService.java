package xinyongbang.application.groupchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.group.representation.ApiGroupRepresentation;
import xinyongbang.application.groupchat.command.ListGroupChatCommand;
import xinyongbang.application.groupchat.command.NewGroupChatCommand;
import xinyongbang.application.groupchat.representation.ApiGroupChatRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.enums.AppPushType;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.groupchat.GroupChat;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.groupchat.IGroupChatService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;
import xinyongbang.listener.XXRunnable;
import xinyongbang.listener.command.Push;

import java.util.List;

/**
 * Created by YJH on 2016/5/23.
 */
@Service("apiGroupChatAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiGroupChatAppService implements IApiGroupChatAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IGroupChatService groupChatService;

    @Override
    public ApiResponse addGroupChat(NewGroupChatCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroup())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群id(group)字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getContent())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "内容(content)字段不能为空", null);
        }
        if (null == command.getChatType()) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "内容类型(chatType)字段不能为空", null);
        }
        GroupChat data = groupChatService.apiCreate(command);
        for (User item : data.getGroup().getUserList()) {
            if (!item.getId().equals(command.getSendUser()) && XXRunnable.user_ip.containsKey(item.getUserName())) {
                ApiGroupChatRepresentation pushData = mappingService.map(data, ApiGroupChatRepresentation.class, false);
                Push push = new Push(AppPushType.NEW_GROUP_CHAT.getValue(), pushData);
                XXRunnable.send(item.getUserName(), push);
            }
        }
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse chatList(ListGroupChatCommand command) {
        if (CoreStringUtils.isEmpty(command.getGroup())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "群id(group)字段不能为空", null);
        }
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<GroupChat> pagination = groupChatService.apiPagination(command);
        List<ApiGroupChatRepresentation> data = mappingService.mapAsList(pagination.getData(), ApiGroupChatRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(),
                new Pagination<ApiGroupChatRepresentation>(data, pagination.getCount(), pagination.getPage(),
                        pagination.getPageSize()));
    }

}
