package xinyongbang.application.friend.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.friend.representation.FriendRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.friend.Friend;

/**
 * Created by YJH on 2016/4/22.
 */
@Component
public class FriendRepresentationMapper extends CustomMapper<Friend, FriendRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Friend friend, FriendRepresentation representation, MappingContext context) {
        if (null != friend.getUser()) {
            UserRepresentation data = mappingService.map(friend.getUser(), UserRepresentation.class, false);
            representation.setUser(data);
        }
        if (null != friend.getFriend()) {
            UserRepresentation data = mappingService.map(friend.getFriend(), UserRepresentation.class, false);
            representation.setFriend(data);
        }
    }

}
