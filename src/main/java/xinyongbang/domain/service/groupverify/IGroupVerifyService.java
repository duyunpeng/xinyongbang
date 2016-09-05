package xinyongbang.domain.service.groupverify;

import xinyongbang.application.groupverify.command.AuthGroupVerifyCommand;
import xinyongbang.application.groupverify.command.ListGroupVerifyCommand;
import xinyongbang.application.groupverify.command.NewGroupVerifyCommand;
import xinyongbang.domain.model.groupverify.GroupVerify;

import java.util.List;

/**
 * Created by YJH on 2016/5/25.
 */
public interface IGroupVerifyService {

    //api
    List<GroupVerify> apiInvitationUser(NewGroupVerifyCommand command);

    GroupVerify apiJoinGroup(NewGroupVerifyCommand command);

    GroupVerify apiAuth(AuthGroupVerifyCommand command);

    GroupVerify searchByID(String id);

    List<GroupVerify> apiAuthList(ListGroupVerifyCommand command);
}
