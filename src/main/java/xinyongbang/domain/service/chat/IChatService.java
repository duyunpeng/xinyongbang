package xinyongbang.domain.service.chat;


import xinyongbang.application.chat.command.ListChatCommand;
import xinyongbang.application.chat.command.NewChatCommand;
import xinyongbang.application.chat.command.UnreadChatCommand;
import xinyongbang.application.chat.command.UpdateStatusCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.chat.Chat;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IChatService {
    Chat apiNewChat(NewChatCommand command);

    void apiUpdateStatus(UpdateStatusCommand command);

    Chat searchByID(String id);

    Pagination<Chat> apiList(ListChatCommand command);

    List<Chat> apiUnread(UnreadChatCommand command);

}
