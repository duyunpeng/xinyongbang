package xinyongbang.domain.service.follow;

import xinyongbang.application.follow.command.FollowCommand;
import xinyongbang.application.follow.command.ListFollowCommand;
import xinyongbang.domain.model.follow.Follow;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by dw on 2016/6/12.
 */
public interface IFollowService {


    Follow apiAddFollow(FollowCommand command);

    void apiCancelFollow(FollowCommand command);

    Follow searchByUser(String user, String followUser);

    Pagination<Follow> apiList(ListFollowCommand command);
}
