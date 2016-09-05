package xinyongbang.application.golddetailed;

import xinyongbang.application.golddetailed.command.ListGoldDetailedCommand;
import xinyongbang.application.golddetailed.representation.GoldDetailedRepresentation;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by YJH on 2016/4/19.
 */
public interface IGoldDetailedAppService {
    Pagination<GoldDetailedRepresentation> pagination(ListGoldDetailedCommand command);

    GoldDetailedRepresentation searchByID(String id);
}
