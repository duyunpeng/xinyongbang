package xinyongbang.domain.service.friend;

import xinyongbang.application.friend.command.AddFriendCommand;
import xinyongbang.application.friend.command.AnswerCommand;
import xinyongbang.application.friend.command.ListFriendCommand;
import xinyongbang.domain.model.friend.Friend;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IFriendService {

    Friend searchByID(String id);


    //Api 方法
    Pagination<Friend> apiFriendList(ListFriendCommand command);

    Friend apiAddFriend(AddFriendCommand command);

    Friend apiAnswer(AnswerCommand command);

    void apiDelete(ListFriendCommand command);

    Pagination<Friend> apiWaitAnswerList(ListFriendCommand command);
}
