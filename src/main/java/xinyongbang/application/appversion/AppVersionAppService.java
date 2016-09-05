package xinyongbang.application.appversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.appversion.command.CreateAppVersionCommand;
import xinyongbang.application.appversion.command.EditAppVersionCommand;
import xinyongbang.application.appversion.command.ListAppVersionCommand;
import xinyongbang.application.appversion.representation.AppVersionRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.appversion.AppVersion;
import xinyongbang.domain.service.appversion.IAppVersionService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/19.
 */
@Service("appVersionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AppVersionAppService implements IAppVersionAppService {

    @Autowired
    private IAppVersionService appVersionService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<AppVersionRepresentation> pagination(ListAppVersionCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<AppVersion> pagination=appVersionService.pagination(command);
        List<AppVersionRepresentation> data=mappingService.mapAsList(pagination.getData(),AppVersionRepresentation.class);
        return new Pagination<AppVersionRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public AppVersionRepresentation searchByID(String id) {
        return mappingService.map(appVersionService.searchByID(id),AppVersionRepresentation.class,false);
    }

    @Override
    public AppVersionRepresentation create(CreateAppVersionCommand command) {
        return mappingService.map(appVersionService.create(command),AppVersionRepresentation.class,false);
    }

    @Override
    public AppVersionRepresentation edit(EditAppVersionCommand command) {
        return mappingService.map(appVersionService.edit(command),AppVersionRepresentation.class,false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        appVersionService.updateStatus(command);
    }
}
