package xinyongbang.domain.service.role;

import xinyongbang.application.role.command.CreateRoleCommand;
import xinyongbang.application.role.command.EditRoleCommand;
import xinyongbang.application.role.command.ListRoleCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.role.Role;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IRoleService {

    Pagination<Role> pagination(ListRoleCommand command);

    List<Role> list(ListRoleCommand command);

    Role searchByID(String id);

    Role searchByName(String name, String appKey);

    Role create(CreateRoleCommand command);

    Role edit(EditRoleCommand command);

    void updateStatus(SharedCommand command);

    List<Role> searchByIDs(List<String> ids);
}
