package xinyongbang.infrastructure.persistence.hibernate.chat;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.chat.Chat;
import xinyongbang.domain.model.chat.IChatRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/22.
 */
@Repository("chatRepository")
public class ChatRepository extends AbstractHibernateGenericRepository<Chat, String>
        implements IChatRepository<Chat, String> {
}
