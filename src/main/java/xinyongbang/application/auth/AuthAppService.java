package xinyongbang.application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.account.IAccountAppService;
import xinyongbang.application.account.representation.AccountRepresentation;
import xinyongbang.application.auth.command.LoginCommand;
import xinyongbang.application.permission.IPermissionAppService;
import xinyongbang.application.permission.command.ListPermissionCommand;
import xinyongbang.application.permission.representation.PermissionRepresentation;
import xinyongbang.core.common.Constants;
import xinyongbang.core.enums.EnableStatus;

import java.util.List;

/**
 * Created by YJH on 2016/4/5.
 */
@Service("authAppService")
public class AuthAppService implements IAuthAppService {

    @Autowired
    private IAccountAppService accountAppService;

    @Autowired
    private IPermissionAppService permissionAppService;

    @Override
    public AccountRepresentation searchByAccountName(String userName, String appKey) {
        return accountAppService.searchByAccountName(userName, appKey);
    }

    @Override
    public List<PermissionRepresentation> findAllPermission() {
        ListPermissionCommand command = new ListPermissionCommand();
        command.setStatus(EnableStatus.ENABLE);
        command.setAppKey(Constants.APP_KRY);
        return permissionAppService.list(command);
    }

    @Override
    public AccountRepresentation login(LoginCommand command) {
        return accountAppService.login(command);
    }
}
