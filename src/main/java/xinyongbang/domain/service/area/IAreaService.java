package xinyongbang.domain.service.area;


import xinyongbang.application.area.command.CreateAreaCommand;
import xinyongbang.application.area.command.EditAreaCommand;
import xinyongbang.application.area.command.ListAreaCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.area.Area;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/14.
 */
public interface IAreaService {
    Pagination<Area> pagination(ListAreaCommand command);

    Area searchByID(String id);

    Area create(CreateAreaCommand command);

    Area edit(EditAreaCommand command);

    void updateStatus(SharedCommand command);

    List<Area> list(ListAreaCommand command);
}
