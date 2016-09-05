package xinyongbang.domain.service.user;

import org.hibernate.LockOptions;
import xinyongbang.application.account.command.ResetPasswordCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.*;
import xinyongbang.domain.model.user.User;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
public interface IUserService {
    Pagination<User> pagination(ListUserCommand command);

    User searchByName(String userName);

    User searchByID(String id);

    User searchByID(String id, LockOptions lockOptions);

    User create(CreateUserCommand command);

    User edit(EditUserCommand command);

    void update(User user);

    User approvePass(SharedCommand command);

    void updateCredit();

    void shareAddGold(String id);

    //api方法
    void apiCreate(RegisterCommand command);

    void apiResetPassword(ResetPasswordCommand command);

    void apiResetPayPassword(ResetPasswordCommand command);

    void apiUpdateHeadPic(EditUserCommand command);

    void apiUpdateInfo(EditUserCommand command);

    void apiFlushLocation(FlushLocationCommand command);

    Pagination<User> apiPagination(ListUserCommand command);

    List<User> apiFindFriend(ListUserCommand command);

    Pagination<User> apiUserApprove(ListUserCommand command);

    User apiApproveReject(SharedCommand command);

    List<User> apiContactsList(ListUserCommand command);

    void apiChangeUserName(ChangeUserNameCommand command);
}
