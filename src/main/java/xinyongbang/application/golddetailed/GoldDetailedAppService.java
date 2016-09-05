package xinyongbang.application.golddetailed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.golddetailed.command.ListGoldDetailedCommand;
import xinyongbang.application.golddetailed.representation.GoldDetailedRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.golddetailed.GoldDetailed;
import xinyongbang.domain.service.golddetailed.IGoldDetailedService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
@Service("goldDetailedAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class GoldDetailedAppService implements IGoldDetailedAppService {

    @Autowired
    private IGoldDetailedService goldDetailedService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<GoldDetailedRepresentation> pagination(ListGoldDetailedCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<GoldDetailed> pagination = goldDetailedService.pagination(command);
        List<GoldDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), GoldDetailedRepresentation.class);
        return new Pagination<GoldDetailedRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public GoldDetailedRepresentation searchByID(String id) {
        return mappingService.map(goldDetailedService.searchByID(id), GoldDetailedRepresentation.class, false);
    }
}
