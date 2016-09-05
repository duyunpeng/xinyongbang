package xinyongbang.application.appversion.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.domain.model.area.Area;

/**
 * Created by lvdi on 2016/4/14.
 */
@Component
public class AppVersionRepresentationMapper extends CustomMapper<Area, AreaRepresentation> {


}
