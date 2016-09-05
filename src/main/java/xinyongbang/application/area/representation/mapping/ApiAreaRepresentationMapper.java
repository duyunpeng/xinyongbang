package xinyongbang.application.area.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import xinyongbang.application.area.representation.ApiAreaRepresentation;
import xinyongbang.domain.model.area.Area;

/**
 * Created by YJH on 2016/5/19.
 */
@Component
public class ApiAreaRepresentationMapper extends CustomMapper<Area, ApiAreaRepresentation> {

    public void mapAtoB(Area area, ApiAreaRepresentation representation, MappingContext context) {
        if (null != area.getParent()) {
            representation.setParent(area.getParent().getId());
            representation.setParentName(area.getParent().getName());
        }
    }

}
