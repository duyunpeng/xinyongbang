package xinyongbang.application.collection.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.chat.representation.ApiChatRepresentation;
import xinyongbang.application.collection.representation.ApiCollectionRepresentation;
import xinyongbang.application.groupchat.representation.ApiGroupChatRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.collection.Collection;

/**
 * Created by YJH on 2016/5/24.
 */
@Component
public class ApiCollectionRepresentationMapper extends CustomMapper<Collection, ApiCollectionRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Collection collection, ApiCollectionRepresentation representation, MappingContext context) {
        if (null != collection.getUser()) {
            representation.setUser(collection.getUser().getId());
            representation.setUserName(collection.getUser().getUserName());
        }
        if (null != collection.getChat()) {
            ApiChatRepresentation data = mappingService.map(collection.getChat(), ApiChatRepresentation.class, false);
            representation.setChat(data);
        }
        if (null != collection.getGroupChat()) {
            ApiGroupChatRepresentation data = mappingService.map(collection.getGroupChat(), ApiGroupChatRepresentation.class, false);
            representation.setGroupChat(data);
        }
    }

}
