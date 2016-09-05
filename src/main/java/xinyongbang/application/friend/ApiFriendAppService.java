package xinyongbang.application.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.friend.command.AddFriendCommand;
import xinyongbang.application.friend.command.AnswerCommand;
import xinyongbang.application.friend.command.ListFriendCommand;
import xinyongbang.application.friend.representation.ApiFriendRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.enums.AppPushType;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.friend.Friend;
import xinyongbang.domain.service.friend.IFriendService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;
import xinyongbang.listener.XXRunnable;
import xinyongbang.listener.command.Push;

import java.util.List;

/**
 * Created by YJH on 2016/5/3.
 */
@Service("apiFriendAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiFriendAppService implements IApiFriendAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IFriendService friendService;


    @Override
    @Transactional(readOnly = true)
    public ApiResponse friendList(ListFriendCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Friend> pagination = friendService.apiFriendList(command);
        List<ApiFriendRepresentation> data = mappingService.mapAsList(pagination.getData(), ApiFriendRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(),
                new Pagination<ApiFriendRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()));
    }

    @Override
    public ApiResponse addFriend(AddFriendCommand command) {
        if (CoreStringUtils.isEmpty(command.getFriend())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "好友账号(friend)不能为空", null);
        }
        ApiFriendRepresentation data = mappingService.map(friendService.apiAddFriend(command), ApiFriendRepresentation.class, false);
        // 推送
        if (XXRunnable.user_ip.containsKey(data.getFriend().getUserName())) {
            XXRunnable.send(data.getFriend().getUserName(), new Push(AppPushType.NEW_FRIEND.getValue(), data));
        }
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse deleteFriend(ListFriendCommand command) {

        if (CoreStringUtils.isEmpty(command.getFriendUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "好友账号(friendUserName)不能为空", null);
        }

        friendService.apiDelete(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse answer(AnswerCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "好友id(id)不能为空", null);
        }
        if (null == command.getVerifyStatus()) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "验证结果(verifyStatus)不能为空", null);
        }
        ApiFriendRepresentation data = mappingService.map(friendService.apiAnswer(command), ApiFriendRepresentation.class, false);
        // 推送
        if (XXRunnable.user_ip.containsKey(data.getFriend().getUserName())) {
            XXRunnable.send(data.getFriend().getUserName(), new Push(AppPushType.NEW_FRIEND_ANSWER.getValue(), data));
        }
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse waitAnswerList(ListFriendCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Friend> pagination = friendService.apiWaitAnswerList(command);
        List<ApiFriendRepresentation> data = mappingService.mapAsList(pagination.getData(), ApiFriendRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(),
                new Pagination<ApiFriendRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()));
    }


}
