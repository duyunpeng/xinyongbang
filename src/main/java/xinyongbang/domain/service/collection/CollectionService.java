package xinyongbang.domain.service.collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.collection.command.ListCollectionCommand;
import xinyongbang.application.collection.command.NewCollectionCommand;
import xinyongbang.domain.model.chat.Chat;
import xinyongbang.domain.model.collection.Collection;
import xinyongbang.domain.model.collection.ICollectionRepository;
import xinyongbang.domain.model.groupchat.GroupChat;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.chat.IChatService;
import xinyongbang.domain.service.groupchat.IGroupChatService;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/5/24.
 */
@Service("collectionService")
public class CollectionService implements ICollectionService {

    @Autowired
    private ICollectionRepository<Collection, String> collectionRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IChatService chatService;

    @Autowired
    private IGroupChatService groupChatService;

    @Override
    public void apiCreate(NewCollectionCommand command) {
        User user = userService.searchByID(command.getUser());
        Chat chat = null;
        GroupChat groupChat = null;
        if (command.getSource().equals("chat")) {
            chat = chatService.searchByID(command.getChat());
        } else {
            groupChat = groupChatService.searchByID(command.getGroupChat());
        }
        Collection collection = new Collection(user, command.getSource(), chat, groupChat);
        collectionRepository.save(collection);
    }

    @Override
    public Pagination<Collection> apiPagination(ListCollectionCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("user.id", command.getUser()));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return collectionRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }
}
