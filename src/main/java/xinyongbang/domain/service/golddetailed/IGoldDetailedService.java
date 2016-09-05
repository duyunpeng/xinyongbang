package xinyongbang.domain.service.golddetailed;

import xinyongbang.application.golddetailed.command.CreateGoldDetailedCommand;
import xinyongbang.application.golddetailed.command.ListGoldDetailedCommand;
import xinyongbang.domain.model.golddetailed.GoldDetailed;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by YJH on 2016/4/19.
 */
public interface IGoldDetailedService {
    Pagination<GoldDetailed> pagination(ListGoldDetailedCommand command);

    GoldDetailed create(CreateGoldDetailedCommand command);

    GoldDetailed searchByID(String id);
}
