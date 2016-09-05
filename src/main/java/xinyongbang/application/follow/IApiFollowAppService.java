package xinyongbang.application.follow;

import xinyongbang.application.follow.command.FollowCommand;
import xinyongbang.application.follow.command.ListFollowCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by dw on 2016/6/12.
 */
public interface IApiFollowAppService {


    ApiResponse addFollow(FollowCommand command);

    ApiResponse cancelFollow(FollowCommand command);

    ApiResponse list(ListFollowCommand command);
}
