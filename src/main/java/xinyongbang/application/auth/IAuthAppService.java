package xinyongbang.application.auth;


import xinyongbang.application.account.representation.AccountRepresentation;
import xinyongbang.application.auth.command.LoginCommand;
import xinyongbang.application.permission.representation.PermissionRepresentation;

import java.util.List;

/**
 * Created by YJH on 2016/4/5.
 */
public interface IAuthAppService {
    AccountRepresentation searchByAccountName(String userName, String appKey);

    List<PermissionRepresentation> findAllPermission();

    AccountRepresentation login(LoginCommand command);
}
