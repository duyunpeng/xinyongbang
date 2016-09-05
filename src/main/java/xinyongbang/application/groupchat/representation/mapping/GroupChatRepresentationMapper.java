package xinyongbang.application.groupchat.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.group.representation.GroupRepresentation;
import xinyongbang.application.groupchat.representation.GroupChatRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.groupchat.GroupChat;

/**
 * Created by YJH on 2016/5/23.
 */
@Component
public class GroupChatRepresentationMapper extends CustomMapper<GroupChat, GroupChatRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(GroupChat groupChat, GroupChatRepresentation representation, MappingContext context) {
        if (null != groupChat.getGroup()) {
            GroupRepresentation data = mappingService.map(groupChat.getGroup(), GroupRepresentation.class, false);
            representation.setGroup(data);
        }
        if (null != groupChat.getUser()) {
            UserRepresentation data = mappingService.map(groupChat.getUser(), UserRepresentation.class, false);
            representation.setUser(data);
        }
    }

}
