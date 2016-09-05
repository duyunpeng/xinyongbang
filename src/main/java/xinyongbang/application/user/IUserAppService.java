package xinyongbang.application.user;

import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.CreateUserCommand;
import xinyongbang.application.user.command.EditUserCommand;
import xinyongbang.application.user.command.ListUserCommand;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by YJH on 2016/4/19.
 */
public interface IUserAppService {

    Pagination<UserRepresentation> pagination(ListUserCommand command);

    UserRepresentation searchByID(String id);

    UserRepresentation create(CreateUserCommand command);

    UserRepresentation edit(EditUserCommand command);

    Pagination<UserRepresentation> userApprove(ListUserCommand command);

    UserRepresentation approvePass(SharedCommand command);

    UserRepresentation approveReject(SharedCommand command);

    public void updateCredit();

    void shareAddGold(String id);
}
