package xinyongbang.application.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.friend.representation.FriendRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.service.friend.IFriendService;

/**
 * Created by YJH on 2016/4/22.
 */
@Service("friendAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class FriendAppService implements IFriendAppService {

    @Autowired
    private IFriendService friendService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public FriendRepresentation searchByID(String id) {
        return mappingService.map(friendService.searchByID(id),FriendRepresentation.class,false);
    }

}
