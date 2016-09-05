package xinyongbang.application.permission;


import xinyongbang.application.permission.command.CreatePermissionCommand;
import xinyongbang.application.permission.command.EditPermissionCommand;
import xinyongbang.application.permission.command.ListPermissionCommand;
import xinyongbang.application.permission.representation.PermissionRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IPermissionAppService {

    Pagination<PermissionRepresentation> pagination(ListPermissionCommand command);

    Pagination<PermissionRepresentation> paginationJSON(ListPermissionCommand command);

    List<PermissionRepresentation> list(ListPermissionCommand command);

    PermissionRepresentation searchByID(String id);

    PermissionRepresentation searchByName(String name, String appKey);

    PermissionRepresentation create(CreatePermissionCommand command);

    PermissionRepresentation edit(EditPermissionCommand command);

    void updateStatus(SharedCommand command);

}
