package xinyongbang.application.friend;

import xinyongbang.application.friend.representation.FriendRepresentation;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IFriendAppService {
    FriendRepresentation searchByID(String id);
}
