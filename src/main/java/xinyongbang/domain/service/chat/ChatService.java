package xinyongbang.domain.service.chat;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.chat.command.ListChatCommand;
import xinyongbang.application.chat.command.NewChatCommand;
import xinyongbang.application.chat.command.UnreadChatCommand;
import xinyongbang.application.chat.command.UpdateStatusCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.enums.ChatType;
import xinyongbang.core.enums.YesOrNoStatus;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.chat.Chat;
import xinyongbang.domain.model.chat.IChatRepository;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/4/22.
 */
@Service("chatService")
public class ChatService implements IChatService {

    @Autowired
    private IChatRepository<Chat, String> chatRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    public Chat apiNewChat(NewChatCommand command) {
        User sendUser = userService.searchByID(command.getSendUser());
        User receiveUser = userService.searchByName(command.getReceiveUser());
        if (null == receiveUser) {
            throw new NoFoundException("接收用户不存在");
        }
        if (command.getChatType() == ChatType.IMG) {
            command.setContent(fileUploadService.moveToChatImg(command.getContent()));
        }
        Chat chat = new Chat(sendUser, receiveUser, YesOrNoStatus.NO, command.getContent(), command.getChatType());
        chatRepository.save(chat);

        return chat;
    }

    @Override
    public void apiUpdateStatus(UpdateStatusCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("receiveStatus", YesOrNoStatus.NO));
        criterionList.add(Restrictions.eq("receiveUser.id", command.getUser()));
        criterionList.add(Restrictions.eq("sendUser.userName", command.getFriend()));
        Map<String, String> alias = new HashMap<String, String>();
        alias.put("sendUser", "sendUser");
        List<Chat> chatList = chatRepository.list(criterionList, null, null, null, alias);
        for (Chat chat : chatList) {
            chat.changeReceiveStatus(YesOrNoStatus.YES);
            chatRepository.update(chat);
        }
    }

    @Override
    public Chat searchByID(String id) {
        Chat chat = chatRepository.getById(id);
        if (null == chat) {
            throw new NoFoundException("没有找到ID[" + id + "]的Chat数据");
        }
        return chat;
    }

    @Override
    public Pagination<Chat> apiList(ListChatCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        List<Order> orderList = new ArrayList<Order>();
        criterionList.add(
                Restrictions.or(
                        Restrictions.and(
                                Restrictions.eq("sendUser.id", command.getSendUser()), Restrictions.eq("receiveUser.userName", command.getReceiveUser())),
                        Restrictions.and(
                                Restrictions.eq("receiveUser.id", command.getSendUser()), Restrictions.eq("sendUser.userName", command.getReceiveUser()))
                ));
        aliasMap.put("receiveUser", "receiveUser");
        aliasMap.put("sendUser", "sendUser");
        orderList.add(Order.desc("createDate"));
        return chatRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public List<Chat> apiUnread(UnreadChatCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        criterionList.add(Restrictions.eq("receiveUser.id", command.getReceiveUser()));
        criterionList.add(Restrictions.eq("receiveStatus", YesOrNoStatus.NO));
        orderList.add(Order.desc("createDate"));
        return chatRepository.list(criterionList, orderList);
    }
}
