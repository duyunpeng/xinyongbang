package xinyongbang.application.chat.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.chat.representation.ChatRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.chat.Chat;

/**
 * Created by YJH on 2016/4/22.
 */
@Component
public class ChatRepresentationMapper extends CustomMapper<Chat, ChatRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Chat chat, ChatRepresentation representation, MappingContext context) {
        if (null != chat.getSendUser()) {
            UserRepresentation data = mappingService.map(chat.getSendUser(), UserRepresentation.class, false);
            representation.setSendUser(data);
        }
        if (null != chat.getReceiveUser()) {
            UserRepresentation data = mappingService.map(chat.getReceiveUser(), UserRepresentation.class, false);
            representation.setReceiveUser(data);
        }
    }

}
