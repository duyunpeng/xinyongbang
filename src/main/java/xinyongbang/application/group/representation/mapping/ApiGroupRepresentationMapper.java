package xinyongbang.application.group.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.group.representation.ApiGroupRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.user.representation.ApiUserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jm on 16-5-19.
 */
@Component
public class ApiGroupRepresentationMapper extends CustomMapper<Group, ApiGroupRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Group group, ApiGroupRepresentation representation, MappingContext context) {
        if (null != group.getUser()) {
            representation.setUser(group.getUser().getId());
            representation.setUserName(group.getUser().getUserName());
        }
        if (null != group.getUserList()) {
            List<ApiUserRepresentation> data = mappingService.mapAsList(group.getUserList(), ApiUserRepresentation.class);
            representation.setUserList(data);
        }
        if (null != group.getPicture()) {
            PictureRepresentation data = mappingService.map(group.getPicture(), PictureRepresentation.class, false);
            representation.setPicture(data);
        }
    }
}

