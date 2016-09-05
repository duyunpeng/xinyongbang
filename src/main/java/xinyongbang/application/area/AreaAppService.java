package xinyongbang.application.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.area.command.CreateAreaCommand;
import xinyongbang.application.area.command.EditAreaCommand;
import xinyongbang.application.area.command.ListAreaCommand;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.area.Area;
import xinyongbang.domain.service.area.IAreaService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/14.
 */
@Service("areaAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AreaAppService implements IAreaAppService {

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<AreaRepresentation> pagination(ListAreaCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Area> pagination = areaService.pagination(command);
        List<AreaRepresentation> data = mappingService.mapAsList(pagination.getData(), AreaRepresentation.class);
        return new Pagination<AreaRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public AreaRepresentation searchByID(String id) {
        return mappingService.map(areaService.searchByID(id), AreaRepresentation.class, false);
    }

    @Override
    public AreaRepresentation create(CreateAreaCommand command) {
        return mappingService.map(areaService.create(command), AreaRepresentation.class, false);
    }

    @Override
    public AreaRepresentation edit(EditAreaCommand command) {
        return mappingService.map(areaService.edit(command), AreaRepresentation.class, false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        areaService.updateStatus(command);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AreaRepresentation> listJSON(ListAreaCommand command) {
        return mappingService.mapAsList(areaService.list(command),AreaRepresentation.class);
    }
}
