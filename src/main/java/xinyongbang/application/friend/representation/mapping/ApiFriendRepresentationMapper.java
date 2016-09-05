package xinyongbang.application.friend.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.friend.representation.ApiFriendRepresentation;
import xinyongbang.application.user.representation.ApiUserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.friend.Friend;

/**
 * Created by YJH on 2016/5/18.
 */
@Component
public class ApiFriendRepresentationMapper extends CustomMapper<Friend, ApiFriendRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Friend friend, ApiFriendRepresentation representation, MappingContext context) {
        if (null != friend.getUser()) {
            ApiUserRepresentation data = mappingService.map(friend.getUser(), ApiUserRepresentation.class, false);
            representation.setUser(data);
        }
        if (null != friend.getFriend()) {
            ApiUserRepresentation data = mappingService.map(friend.getFriend(), ApiUserRepresentation.class, false);
            representation.setFriend(data);
        }
    }

}
