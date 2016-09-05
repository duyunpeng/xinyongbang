package xinyongbang.domain.service.groupverify;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.groupverify.command.AuthGroupVerifyCommand;
import xinyongbang.application.groupverify.command.ListGroupVerifyCommand;
import xinyongbang.application.groupverify.command.NewGroupVerifyCommand;
import xinyongbang.core.enums.VerifyStatus;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.exception.NotAdminException;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.model.groupverify.GroupVerify;
import xinyongbang.domain.model.groupverify.IGroupVerifyRepository;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.group.IGroupService;
import xinyongbang.domain.service.user.IUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/5/25.
 */
@Service("groupVerifyService")
public class GroupVerifyService implements IGroupVerifyService {

    @Autowired
    private IGroupVerifyRepository<GroupVerify, String> groupVerifyRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @Override
    public List<GroupVerify> apiInvitationUser(NewGroupVerifyCommand command) {
        User user = userService.searchByID(command.getUser());
        Group group = groupService.searchByID(command.getGroup());
        if (user.getId().equals(group.getUser().getId())) {
            List<GroupVerify> groupVerifyList = new ArrayList<GroupVerify>();
            List<User> groupUserList = group.getUserList();
            for (String item : command.getInvitationUser()) {
                User invitationUser = userService.searchByID(item);
                GroupVerify groupVerify = new GroupVerify(invitationUser, group, null, VerifyStatus.ACCEPT);
                groupVerifyRepository.save(groupVerify);
                groupVerifyList.add(groupVerify);
                if (!groupUserList.contains(invitationUser)) {
                    groupUserList.add(invitationUser);
                }
            }
            group.changeUserList(groupUserList);
            groupService.update(group);
            return groupVerifyList;
        } else {
            throw new NotAdminException("不是群主,不能管理群");
        }
    }

    @Override
    public GroupVerify apiJoinGroup(NewGroupVerifyCommand command) {
        User user = userService.searchByID(command.getUser());
        Group group = groupService.searchByID(command.getGroup());
        GroupVerify groupVerify = new GroupVerify(user, group, command.getValidationMessage(), VerifyStatus.WAIT);
        return groupVerify;
    }

    @Override
    public GroupVerify apiAuth(AuthGroupVerifyCommand command) {
        User user = userService.searchByID(command.getUser());
        GroupVerify groupVerify = this.searchByID(command.getGroupVerifyId());
        if (user.getId().equals(groupVerify.getGroup().getUser().getId())) {
            if (command.getVerifyStatus() == VerifyStatus.ACCEPT) {
                groupVerify.changeVerifyStatus(command.getVerifyStatus());
                groupVerifyRepository.update(groupVerify);

                Group group = groupVerify.getGroup();
                List<User> userList = group.getUserList();
                userList.add(groupVerify.getApplicantUser());
                groupService.update(group);

                return groupVerify;
            } else if (command.getVerifyStatus() == VerifyStatus.REFUSE) {
                groupVerify.changeVerifyStatus(command.getVerifyStatus());
                groupVerifyRepository.update(groupVerify);
                return groupVerify;
            } else {
                throw new ExistException("处理结果错误");
            }
        } else {
            throw new NotAdminException("不是群主,不能管理群");
        }
    }

    @Override
    public GroupVerify searchByID(String id) {
        GroupVerify groupVerify = groupVerifyRepository.getById(id);
        if (null == groupVerify) {
            throw new NoFoundException("没有找到ID[" + id + "]的GroupVerify数据");
        }
        return groupVerify;
    }

    @Override
    public List<GroupVerify> apiAuthList(ListGroupVerifyCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("verifyStatus", null == command.getVerifyStatus() ? VerifyStatus.WAIT : command.getVerifyStatus()));
        criterionList.add(Restrictions.eq("group.user.id", command.getUser()));
        Map<String, String> alias = new HashMap<String, String>();
        alias.put("group", "group");
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return groupVerifyRepository.list(criterionList, orderList, null, null, alias);
    }
}
