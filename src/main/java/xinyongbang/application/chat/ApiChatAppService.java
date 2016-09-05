package xinyongbang.application.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.chat.command.ListChatCommand;
import xinyongbang.application.chat.command.NewChatCommand;
import xinyongbang.application.chat.command.UnreadChatCommand;
import xinyongbang.application.chat.command.UpdateStatusCommand;
import xinyongbang.application.chat.representation.ApiChatRepresentation;
import xinyongbang.application.chat.representation.ChatRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.enums.AppPushType;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.chat.Chat;
import xinyongbang.domain.service.chat.IChatService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;
import xinyongbang.listener.XXRunnable;
import xinyongbang.listener.command.Push;

import java.util.List;

/**
 * Created by YJH on 2016/5/16.
 */
@Service("apiChatAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiChatAppService implements IApiChatAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IChatService chatService;

    @Override
    public ApiResponse newChat(NewChatCommand command) {
        if (CoreStringUtils.isEmpty(command.getReceiveUser())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "接收用户账号(receiveUser)不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getContent())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "内容不能为空(content)不能为空", null);
        }
        if (null == command.getChatType()) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "内容类型不能为空(chatType)不能为空", null);
        }
        ApiChatRepresentation data = mappingService.map(chatService.apiNewChat(command), ApiChatRepresentation.class, false);
        // 推送
        if (XXRunnable.user_ip.containsKey(data.getReceiveUserName())) {
            XXRunnable.send(data.getReceiveUserName(), new Push(AppPushType.NEW_CHAT.getValue(), data));
        }
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse updateStatus(UpdateStatusCommand command) {
        if (CoreStringUtils.isEmpty(command.getFriend())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "好友账号(friend)不能为空", null);
        }
        chatService.apiUpdateStatus(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse list(ListChatCommand command) {
        if (CoreStringUtils.isEmpty(command.getReceiveUser())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "接收用户账号(receiveUser)不能为空", null);
        }
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Chat> pagination = chatService.apiList(command);
        List<ApiChatRepresentation> data = mappingService.mapAsList(pagination.getData(), ApiChatRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(),
                new Pagination<ApiChatRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()));
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse unread(UnreadChatCommand command) {
        List<ApiChatRepresentation> data = mappingService.mapAsList(chatService.apiUnread(command), ApiChatRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }
}
