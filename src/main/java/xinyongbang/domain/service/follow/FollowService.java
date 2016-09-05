package xinyongbang.domain.service.follow;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.follow.command.FollowCommand;
import xinyongbang.application.follow.command.ListFollowCommand;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.domain.model.follow.Follow;
import xinyongbang.domain.model.follow.IFollowRepository;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dw on 2016/6/12.
 */
@Service("followService")
public class FollowService implements IFollowService {

    @Autowired
    private IFollowRepository<Follow, String> followRepository;

    @Autowired
    private IUserService userService;

    @Override
    public Follow apiAddFollow(FollowCommand command) {
        User user = userService.searchByID(command.getUser());
        User friendUser = userService.searchByName(command.getFollowUser());
        if (null == friendUser) {
            throw new NoFoundException("好友账号不存在");
        }
        if (null != this.searchByUser(command.getUser(), command.getFollowUser())) {
            throw new ExistException("已经关注");
        }
        Follow follow = new Follow(user, friendUser);
        followRepository.save(follow);

        return follow;
    }

    @Override
    public void apiCancelFollow(FollowCommand command) {
        Follow follow = this.searchByUser(command.getUser(), command.getFollowUser());
        if (null == follow) {
            throw new NoFoundException("数据不存在");
        }
        followRepository.delete(follow);
    }

    @Override
    public Follow searchByUser(String user, String followUser) {
        return followRepository.searchByUser(user, followUser);
    }

    @Override
    public Pagination<Follow> apiList(ListFollowCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("user.id", command.getUser()));
        return followRepository.pagination(command.getPage(), command.getPageSize(), criterionList, null);
    }
}
