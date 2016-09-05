package xinyongbang.application.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.role.command.CreateRoleCommand;
import xinyongbang.application.role.command.EditRoleCommand;
import xinyongbang.application.role.command.ListRoleCommand;
import xinyongbang.application.role.representation.RoleRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.role.Role;
import xinyongbang.domain.service.role.IRoleService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("roleAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class RoleAppService implements IRoleAppService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<RoleRepresentation> paginationJSON(ListRoleCommand command) {
        command.verifyPage();
        command.setName(command.getRoleName());
        Pagination<Role> pagination = roleService.pagination(command);
        List<RoleRepresentation> data = mappingService.mapAsList(pagination.getData(), RoleRepresentation.class);
        return new Pagination<RoleRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<RoleRepresentation> pagination(ListRoleCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Role> pagination = roleService.pagination(command);
        List<RoleRepresentation> data = mappingService.mapAsList(pagination.getData(), RoleRepresentation.class);
        return new Pagination<RoleRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleRepresentation> list(ListRoleCommand command) {
        return mappingService.mapAsList(roleService.list(command), RoleRepresentation.class);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleRepresentation searchByID(String id) {
        return mappingService.map(roleService.searchByID(id), RoleRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleRepresentation searchByName(String name, String appKey) {
        return mappingService.map(roleService.searchByName(name, appKey), RoleRepresentation.class, false);
    }

    @Override
    public RoleRepresentation create(CreateRoleCommand command) {
        return mappingService.map(roleService.create(command), RoleRepresentation.class, false);
    }

    @Override
    public RoleRepresentation edit(EditRoleCommand command) {
        return mappingService.map(roleService.edit(command), RoleRepresentation.class, false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        roleService.updateStatus(command);
    }
}
