package xinyongbang.infrastructure.persistence.hibernate.groupchat;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.groupchat.GroupChat;
import xinyongbang.domain.model.groupchat.IGroupChatRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/5/16.
 */
@Repository("groupChatRepository")
public class GroupChatRepository extends AbstractHibernateGenericRepository<GroupChat, String>
        implements IGroupChatRepository<GroupChat, String> {
}
