package xinyongbang.application.golddetailed.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import xinyongbang.application.golddetailed.representation.ApiGoldDetailedRepresentation;
import xinyongbang.domain.model.golddetailed.GoldDetailed;

/**
 * Created by YJH on 2016/5/18.
 */
@Component
public class ApiGoldDetailedRepresentationMapper extends CustomMapper<GoldDetailed, ApiGoldDetailedRepresentation> {

    public void mapAtoB(GoldDetailed goldDetailed, ApiGoldDetailedRepresentation representation, MappingContext context) {
        if (null != goldDetailed.getUser()) {
            representation.setUser(goldDetailed.getUser().getId());
            representation.setUserName(goldDetailed.getUser().getUserName());
        }
    }

}
