package xinyongbang.application.groupchat.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.groupchat.representation.ApiGroupChatRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.groupchat.GroupChat;

/**
 * Created by YJH on 2016/5/23.
 */
@Component
public class ApiGroupChatRepresentationMapper extends CustomMapper<GroupChat, ApiGroupChatRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(GroupChat groupChat, ApiGroupChatRepresentation representation, MappingContext context) {
        if (null != groupChat.getGroup()) {
            representation.setGroup(groupChat.getGroup().getId());
            representation.setGroupName(groupChat.getGroup().getName());
        }
        if (null != groupChat.getUser()) {
            representation.setUser(groupChat.getUser().getId());
            representation.setUserName(groupChat.getUser().getUserName());
            PictureRepresentation data = mappingService.map(groupChat.getUser().getHeadPic(), PictureRepresentation.class, false);
            representation.setSendUserHead(data);
        }
    }

}
