package xinyongbang.domain.service.group;

import xinyongbang.application.group.command.EditGroupCommand;
import xinyongbang.application.group.command.ListGroupCommand;
import xinyongbang.application.group.command.NewGroupCommand;
import xinyongbang.application.group.representation.ApiGroupRepresentation;
import xinyongbang.domain.model.group.Group;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by jm on 16-5-19.
 */
public interface IGroupService {

    Group searchByID(String id);

    void update(Group group);

    //api
    List<Group> apiGroupList(ListGroupCommand command);

    void apiCreate(NewGroupCommand command);

    Group searchByNO(String groupNo);

    List<Group> apiFindGroup(ListGroupCommand command);

    void apiDeleteGroupUser(ListGroupCommand command);

    List<Group> apiPromotion();

    void apiUpdateInfo(EditGroupCommand command);
}
