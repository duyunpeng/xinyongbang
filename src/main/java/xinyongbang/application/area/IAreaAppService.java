package xinyongbang.application.area;

import xinyongbang.application.area.command.CreateAreaCommand;
import xinyongbang.application.area.command.EditAreaCommand;
import xinyongbang.application.area.command.ListAreaCommand;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/14.
 */
public interface IAreaAppService {

    Pagination<AreaRepresentation> pagination(ListAreaCommand command);

    AreaRepresentation searchByID(String id);

    AreaRepresentation create(CreateAreaCommand command);

    AreaRepresentation edit(EditAreaCommand command);

    void updateStatus(SharedCommand command);

    List<AreaRepresentation> listJSON(ListAreaCommand command);
}
