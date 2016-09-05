package xinyongbang.domain.service.account;


import xinyongbang.application.account.command.*;
import xinyongbang.application.auth.command.LoginCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.account.Account;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAccountService {

    Pagination<Account> pagination(ListAccountCommand command);

    List<Account> list(ListAccountCommand command);

    Account searchByID(String id);

    Account searchByAccountName(String userName, String appKey);

    Account create(CreateAccountCommand command);

    Account edit(EditAccountCommand command);

    void updateStatus(SharedCommand command);

    void resetPassword(ResetPasswordCommand command);

    void authorized(AuthorizeAccountCommand command);

    void updateAppKey(UpdateUserAppKeyCommand command);

    Account login(LoginCommand command);

    List<Account> searchByIDs(List<String> ids);

    List<Account> searchByRoleIDs(List<String> ids);

    void updateHeadPic(UpdateHeadPicCommand command);
}
