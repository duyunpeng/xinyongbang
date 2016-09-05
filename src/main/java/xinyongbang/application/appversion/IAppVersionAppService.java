package xinyongbang.application.appversion;


import xinyongbang.application.appversion.command.CreateAppVersionCommand;
import xinyongbang.application.appversion.command.EditAppVersionCommand;
import xinyongbang.application.appversion.command.ListAppVersionCommand;
import xinyongbang.application.appversion.representation.AppVersionRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by LvDi on 2016/4/19.
 */
public interface IAppVersionAppService {
    Pagination<AppVersionRepresentation> pagination(ListAppVersionCommand command);

    AppVersionRepresentation searchByID(String id);

    AppVersionRepresentation create(CreateAppVersionCommand command);

    AppVersionRepresentation edit(EditAppVersionCommand command);

    void updateStatus(SharedCommand command);


}
