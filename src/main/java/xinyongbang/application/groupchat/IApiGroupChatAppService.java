package xinyongbang.application.groupchat;

import xinyongbang.application.groupchat.command.ListGroupChatCommand;
import xinyongbang.application.groupchat.command.NewGroupChatCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/5/23.
 */
public interface IApiGroupChatAppService {
    ApiResponse addGroupChat(NewGroupChatCommand command);

    ApiResponse chatList(ListGroupChatCommand command);
}
