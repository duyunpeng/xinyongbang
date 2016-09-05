package xinyongbang.application.help.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.application.help.representation.HelpRepresentation;
import xinyongbang.domain.model.help.Help;

/**
 * Created by dw on 2016/5/23.
 */
@Component
public class HelpRepresentationMapper extends CustomMapper<Help, HelpRepresentation> {

}
