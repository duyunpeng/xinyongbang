package xinyongbang.domain.service.permission;


import xinyongbang.application.permission.command.CreatePermissionCommand;
import xinyongbang.application.permission.command.EditPermissionCommand;
import xinyongbang.application.permission.command.ListPermissionCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.permission.Permission;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IPermissionService {

    Pagination<Permission> pagination(ListPermissionCommand command);

    List<Permission> list(ListPermissionCommand command);

    List<Permission> searchByIDs(List<String> ids);

    Permission searchByID(String id);

    Permission searchByName(String name, String appKey);

    Permission create(CreatePermissionCommand command);

    Permission edit(EditPermissionCommand command);

    void updateStatus(SharedCommand command);
}
