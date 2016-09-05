package xinyongbang.application.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.permission.command.CreatePermissionCommand;
import xinyongbang.application.permission.command.EditPermissionCommand;
import xinyongbang.application.permission.command.ListPermissionCommand;
import xinyongbang.application.permission.representation.PermissionRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.permission.Permission;
import xinyongbang.domain.service.permission.IPermissionService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("permissionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PermissionAppService implements IPermissionAppService {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<PermissionRepresentation> pagination(ListPermissionCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Permission> pagination = permissionService.pagination(command);
        List<PermissionRepresentation> data = mappingService.mapAsList(pagination.getData(), PermissionRepresentation.class);
        return new Pagination<PermissionRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<PermissionRepresentation> paginationJSON(ListPermissionCommand command) {
        command.verifyPage();
        command.setName(command.getPermissionName());
        Pagination<Permission> pagination = permissionService.pagination(command);
        List<PermissionRepresentation> data = mappingService.mapAsList(pagination.getData(), PermissionRepresentation.class);
        return new Pagination<PermissionRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionRepresentation> list(ListPermissionCommand command) {
        return mappingService.mapAsList(permissionService.list(command), PermissionRepresentation.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionRepresentation searchByID(String id) {
        return mappingService.map(permissionService.searchByID(id), PermissionRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionRepresentation searchByName(String name, String appKey) {
        return mappingService.map(permissionService.searchByName(name, appKey), PermissionRepresentation.class, false);
    }

    @Override
    public PermissionRepresentation create(CreatePermissionCommand command) {
        PermissionRepresentation permission = mappingService.map(permissionService.create(command), PermissionRepresentation.class, false);
        return permission;
    }

    @Override
    public PermissionRepresentation edit(EditPermissionCommand command) {
        PermissionRepresentation permission = mappingService.map(permissionService.edit(command), PermissionRepresentation.class, false);
        return permission;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        permissionService.updateStatus(command);
    }
}
