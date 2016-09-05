package xinyongbang.domain.service.appversion;

import xinyongbang.application.appversion.command.CreateAppVersionCommand;
import xinyongbang.application.appversion.command.EditAppVersionCommand;
import xinyongbang.application.appversion.command.ListAppVersionCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.appversion.AppVersion;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/19.
 */
public interface IAppVersionService {
    Pagination<AppVersion> pagination(ListAppVersionCommand command);

    AppVersion searchByID(String id);

    AppVersion create(CreateAppVersionCommand command);

    AppVersion edit(EditAppVersionCommand command);

    void updateStatus(SharedCommand command);

    /***********Api 方法 **************/

    List<AppVersion> allList();

}
