package xinyongbang.application.user.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.application.identitycard.representation.IdentityCardRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.user.User;

/**
 * Created by YJH on 2016/4/19.
 */
@Component
public class UserRepresentationMapper extends CustomMapper<User, UserRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(User user, UserRepresentation representation, MappingContext context) {
        if (null != user.getIdentityCard()) {
            IdentityCardRepresentation data = mappingService.map(user.getIdentityCard(), IdentityCardRepresentation.class, false);
            representation.setIdentityCard(data);
        }
        if (null != user.getArea()) {
            AreaRepresentation data = mappingService.map(user.getArea(), AreaRepresentation.class, false);
            representation.setArea(data);
        }
    }

}
