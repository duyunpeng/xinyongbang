package xinyongbang.domain.service.groupchat;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.groupchat.command.ListGroupChatCommand;
import xinyongbang.application.groupchat.command.NewGroupChatCommand;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.model.groupchat.GroupChat;
import xinyongbang.domain.model.groupchat.IGroupChatRepository;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.group.IGroupService;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/5/23.
 */
@Service("groupChatService")
public class GroupChatService implements IGroupChatService {

    @Autowired
    private IGroupChatRepository<GroupChat, String> groupChatRepository;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IUserService userService;

    @Override
    public GroupChat searchByID(String id) {
        GroupChat groupChat = groupChatRepository.getById(id);
        if (null == groupChat) {
            throw new NoFoundException("没有找到ID[" + id + "]的GroupChat数据");
        }
        return groupChat;
    }

    @Override
    public GroupChat apiCreate(NewGroupChatCommand command) {
        Group group = groupService.searchByID(command.getGroup());
        User user = userService.searchByID(command.getSendUser());

        GroupChat groupChat = new GroupChat(group, command.getContent(), user, command.getChatType());
        groupChatRepository.save(groupChat);
        return groupChat;
    }

    @Override
    public Pagination<GroupChat> apiPagination(ListGroupChatCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        criterionList.add(Restrictions.eq("group.id", command.getGroup()));
        orderList.add(Order.desc("createDate"));
        return groupChatRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }
}
