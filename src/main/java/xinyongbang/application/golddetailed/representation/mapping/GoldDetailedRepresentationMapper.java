package xinyongbang.application.golddetailed.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.application.golddetailed.representation.GoldDetailedRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.golddetailed.GoldDetailed;

/**
 * Created by YJH on 2016/4/19.
 */
@Component
public class GoldDetailedRepresentationMapper extends CustomMapper<GoldDetailed, GoldDetailedRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(GoldDetailed goldDetailed, GoldDetailedRepresentation representation, MappingContext context) {
        if (null != goldDetailed.getUser()) {
            UserRepresentation data = mappingService.map(goldDetailed.getUser(), UserRepresentation.class, false);
            representation.setUser(data);
        }
    }

}
