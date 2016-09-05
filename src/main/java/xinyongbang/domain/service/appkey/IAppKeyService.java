package xinyongbang.domain.service.appkey;

import xinyongbang.application.appkey.command.CreateAppKeyCommand;
import xinyongbang.application.appkey.command.EditAppKeyCommand;
import xinyongbang.application.appkey.command.ListAppKeyCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.appkey.AppKey;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAppKeyService {

    List<AppKey> list(ListAppKeyCommand command);

    Pagination<AppKey> pagination(ListAppKeyCommand command);

    AppKey searchByName(String name);

    AppKey create(CreateAppKeyCommand command);

    AppKey searchByID(String id);

    AppKey edit(EditAppKeyCommand command);

    void updateStatus(SharedCommand command);
}
