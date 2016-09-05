package xinyongbang.application.user;

import xinyongbang.application.account.command.ResetPasswordCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.*;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/28.
 */
public interface IApiUserAppService {
    ApiResponse register(RegisterCommand command);

    ApiResponse info(SharedCommand command);

    ApiResponse resetPassword(ResetPasswordCommand command);

    ApiResponse resetPayPassword(ResetPasswordCommand command);

    ApiResponse friendInfo(ListUserCommand command);

    ApiResponse updateHeadPic(EditUserCommand command);

    ApiResponse updateInfo(EditUserCommand command);

    ApiResponse flushLocation( FlushLocationCommand command);

    ApiResponse indexPagination(ListUserCommand command);

    ApiResponse findFriend(ListUserCommand command);

    ApiResponse contactsList(ListUserCommand command);

    ApiResponse changeUserName(ChangeUserNameCommand command);
}
