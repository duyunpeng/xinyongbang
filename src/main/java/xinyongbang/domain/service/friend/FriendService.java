package xinyongbang.domain.service.friend;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.friend.command.AddFriendCommand;
import xinyongbang.application.friend.command.AnswerCommand;
import xinyongbang.application.friend.command.ListFriendCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.enums.VerifyStatus;
import xinyongbang.core.exception.ApiDataException;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.domain.model.friend.Friend;
import xinyongbang.domain.model.friend.IFriendRepository;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
@Service("friendService")
public class FriendService implements IFriendService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IFriendRepository<Friend, String> friendRepository;

    @Autowired
    private IUserService userService;

    @Override
    public Friend searchByID(String id) {
        Friend friend = friendRepository.getById(id);
        if (null == friend) {
            throw new NoFoundException("没有找到ID[" + id + "]的Friend数据");
        }
        return friend;
    }

    @Override
    public Pagination<Friend> apiFriendList(ListFriendCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("user.id", command.getUser()));
        criterionList.add(Restrictions.eq("verifyStatus", VerifyStatus.ACCEPT));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return friendRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Friend apiAddFriend(AddFriendCommand command) {
        Friend uniqueFriend = friendRepository.searchByUser(command.getUser(), command.getFriend());
        if (null != uniqueFriend) {
            if (uniqueFriend.getVerifyStatus() == VerifyStatus.WAIT) {
                throw new ExistException("等待好友答复");
            }
            throw new ExistException("已经是好友");
        }

        User user = userService.searchByID(command.getUser());
        User friendUser = userService.searchByName(command.getFriend());
        if (null == friendUser) {
            throw new NoFoundException("好友账号不存在");
        }
        Friend friend = new Friend(user, friendUser, VerifyStatus.WAIT, command.getValidationMessage());
        friendRepository.save(friend);

        return friend;
    }

    @Override
    public Friend apiAnswer(AnswerCommand command) {
        Friend friend = this.searchByID(command.getId());
        friend.changeVerifyStatus(command.getVerifyStatus());
        friendRepository.update(friend);

        Friend newFriend = new Friend(friend.getFriend(), friend.getUser(), VerifyStatus.ACCEPT, "");
        friendRepository.save(newFriend);
        return friend;
    }

    @Override
    public void apiDelete(ListFriendCommand command) {
        Friend friend = friendRepository.searchByUser(command.getUser(), command.getFriendUserName());
        if (!friend.getUser().getId().equals(command.getUser())) {
            throw new ApiDataException(new ApiResponse(ApiReturnCode.ERROR_DATA));
        }
        friendRepository.delete(friend);

        friend = friendRepository.searchByUser(friend.getFriend().getId(), friend.getUser().getUserName());
        friendRepository.delete(friend);
    }

    @Override
    public Pagination<Friend> apiWaitAnswerList(ListFriendCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("friend.id", command.getUser()));
        criterionList.add(Restrictions.eq("verifyStatus", VerifyStatus.WAIT));
        return friendRepository.pagination(command.getPage(), command.getPageSize(), criterionList, null);
    }

}
