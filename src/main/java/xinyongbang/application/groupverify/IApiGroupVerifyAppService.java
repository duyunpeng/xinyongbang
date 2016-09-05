package xinyongbang.application.groupverify;

import xinyongbang.application.groupverify.command.AuthGroupVerifyCommand;
import xinyongbang.application.groupverify.command.ListGroupVerifyCommand;
import xinyongbang.application.groupverify.command.NewGroupVerifyCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/5/25.
 */
public interface IApiGroupVerifyAppService {
    ApiResponse invitationUser(NewGroupVerifyCommand command);

    ApiResponse joinGroup(NewGroupVerifyCommand command);

    ApiResponse auth(AuthGroupVerifyCommand command);

    ApiResponse authList(ListGroupVerifyCommand command);
}
