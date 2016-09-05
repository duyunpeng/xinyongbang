package xinyongbang.application.follow.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.follow.representation.ApiFollowRepresentation;
import xinyongbang.application.user.representation.ApiUserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.follow.Follow;

/**
 * Created by yjh on 16-6-13.
 */
@Component
public class ApiFollowRepresentationMapper extends CustomMapper<Follow, ApiFollowRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Follow follow, ApiFollowRepresentation representation, MappingContext context) {
        if (null != follow.getFollowUser()) {
            ApiUserRepresentation data = mappingService.map(follow.getFollowUser(), ApiUserRepresentation.class, false);
            representation.setFollowUser(data);
        }
    }

}
