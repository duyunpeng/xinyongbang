package xinyongbang.application.chat.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.chat.representation.ApiChatRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.chat.Chat;

/**
 * Created by YJH on 2016/5/16.
 */
@Component
public class ApiChatRepresentationMapper extends CustomMapper<Chat, ApiChatRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Chat chat, ApiChatRepresentation representation, MappingContext context) {
        if (null != chat.getSendUser()) {
            representation.setSendUser(chat.getSendUser().getId());
            representation.setSendUserName(chat.getSendUser().getUserName());
            PictureRepresentation data = mappingService.map(chat.getSendUser().getHeadPic(), PictureRepresentation.class, false);
            representation.setSendUserHead(data);
            representation.setSendName(chat.getSendUser().getName());
        }
        if (null != chat.getReceiveUser()) {
            representation.setReceiveUser(chat.getReceiveUser().getId());
            representation.setReceiveUserName(chat.getReceiveUser().getUserName());
            representation.setReceiveName(chat.getReceiveUser().getName());
        }
    }

}
