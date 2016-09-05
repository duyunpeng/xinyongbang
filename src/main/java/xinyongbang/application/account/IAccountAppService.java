package xinyongbang.application.account;


import xinyongbang.application.account.command.*;
import xinyongbang.application.account.representation.AccountRepresentation;
import xinyongbang.application.auth.command.LoginCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAccountAppService {

    Pagination<AccountRepresentation> pagination(ListAccountCommand command);

    List<AccountRepresentation> list(ListAccountCommand command);

    AccountRepresentation searchByID(String id);

    AccountRepresentation searchByAccountName(String userName, String appKey);

    AccountRepresentation create(CreateAccountCommand command);

    AccountRepresentation edit(EditAccountCommand command);

    void updateStatus(SharedCommand command);

    void resetPassword(ResetPasswordCommand command);

    void authorized(AuthorizeAccountCommand command);

    void updateAppKey(UpdateUserAppKeyCommand command);

    AccountRepresentation login(LoginCommand command);

    Pagination<AccountRepresentation> paginationJSON(ListAccountCommand command);

    void updateHeadPic(UpdateHeadPicCommand command);
}
