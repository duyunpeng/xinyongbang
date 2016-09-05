package xinyongbang.application.group.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.group.representation.GroupRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.model.picture.Picture;

import java.util.List;

/**
 * Created by YJH on 2016/5/23.
 */
@Component
public class GroupRepresentationMapper extends CustomMapper<Group, GroupRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Group group, GroupRepresentation representation, MappingContext context) {
        if (null != group.getUser()) {
            UserRepresentation data = mappingService.map(group.getUser(), UserRepresentation.class, false);
            representation.setUser(data);
        }
        if (null != group.getUserList()) {
            List<UserRepresentation> data = mappingService.mapAsList(group.getUserList(), UserRepresentation.class);
            representation.setUserList(data);
        }
        if (null != group.getPicture()) {
            PictureRepresentation data = mappingService.map(group.getPicture(), PictureRepresentation.class, false);
            representation.setPicture(data);
        }
    }

}
