package xinyongbang.application.group;

import xinyongbang.application.group.command.EditGroupCommand;
import xinyongbang.application.group.command.ListGroupCommand;
import xinyongbang.application.group.command.NewGroupCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by jm on 16-5-19.
 */
public interface IApiGroupAppService {

    ApiResponse groupList(ListGroupCommand command);

    ApiResponse groupInfo(ListGroupCommand command);

    ApiResponse newGroup(NewGroupCommand command);

    ApiResponse findGroup(ListGroupCommand command);

    ApiResponse deleteGroupUser(ListGroupCommand command);

    ApiResponse promotion();

    ApiResponse updateInfo(EditGroupCommand command);
}
