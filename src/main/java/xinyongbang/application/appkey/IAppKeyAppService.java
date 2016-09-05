package xinyongbang.application.appkey;


import xinyongbang.application.appkey.command.CreateAppKeyCommand;
import xinyongbang.application.appkey.command.EditAppKeyCommand;
import xinyongbang.application.appkey.command.ListAppKeyCommand;
import xinyongbang.application.appkey.representation.AppKeyRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAppKeyAppService {

    List<AppKeyRepresentation> allList();

    List<AppKeyRepresentation> list(ListAppKeyCommand command);

    Pagination<AppKeyRepresentation> pagination(ListAppKeyCommand command);

    Pagination<AppKeyRepresentation> paginationJSON(ListAppKeyCommand command);

    AppKeyRepresentation searchByID(String id);

    AppKeyRepresentation searchByName(String name);

    AppKeyRepresentation create(CreateAppKeyCommand command);

    AppKeyRepresentation edit(EditAppKeyCommand command);

    void updateStatus(SharedCommand command);

}
