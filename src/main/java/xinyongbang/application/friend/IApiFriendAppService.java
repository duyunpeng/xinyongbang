package xinyongbang.application.friend;

import xinyongbang.application.friend.command.AddFriendCommand;
import xinyongbang.application.friend.command.AnswerCommand;
import xinyongbang.application.friend.command.ListFriendCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/5/3.
 */
public interface IApiFriendAppService {
    ApiResponse friendList(ListFriendCommand command);

    ApiResponse addFriend(AddFriendCommand command);

    ApiResponse deleteFriend(ListFriendCommand command);

    ApiResponse answer(AnswerCommand command);

    ApiResponse waitAnswerList(ListFriendCommand command);
}
