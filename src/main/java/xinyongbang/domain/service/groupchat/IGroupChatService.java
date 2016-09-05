package xinyongbang.domain.service.groupchat;

import xinyongbang.application.groupchat.command.ListGroupChatCommand;
import xinyongbang.application.groupchat.command.NewGroupChatCommand;
import xinyongbang.domain.model.groupchat.GroupChat;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by YJH on 2016/5/23.
 */
public interface IGroupChatService {

    GroupChat searchByID(String groupChat);

    //api
    GroupChat apiCreate(NewGroupChatCommand command);

    Pagination<GroupChat> apiPagination(ListGroupChatCommand command);
}
