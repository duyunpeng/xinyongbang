package xinyongbang.application.chat;

import xinyongbang.application.chat.command.ListChatCommand;
import xinyongbang.application.chat.command.NewChatCommand;
import xinyongbang.application.chat.command.UnreadChatCommand;
import xinyongbang.application.chat.command.UpdateStatusCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/5/16.
 */
public interface IApiChatAppService {
    ApiResponse newChat(NewChatCommand command);

    ApiResponse updateStatus(UpdateStatusCommand command);

    ApiResponse list(ListChatCommand command);

    ApiResponse unread(UnreadChatCommand command);
}
