package xinyongbang.application.user.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.area.representation.ApiAreaRepresentation;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.user.representation.ApiUserRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.common.Constants;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.account.Account;
import xinyongbang.domain.model.follow.Follow;
import xinyongbang.domain.model.role.Role;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.clicklike.IClickLickService;
import xinyongbang.domain.service.follow.IFollowService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/5/18.
 */
@Component
public class ApiUserRepresentationMapper extends CustomMapper<User, ApiUserRepresentation> {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IClickLickService clickLickService;

    @Autowired
    private IFollowService followService;

    public void mapAtoB(User user, ApiUserRepresentation representation, MappingContext context) {
        if (null != user.getAppKey()) {
            representation.setAppKey(user.getAppKey().getId());
        }
        if (null != user.getRoles()) {
            List<String> data = new ArrayList<String>();
            for (Role role : user.getRoles()) {
                data.add(role.getId());
            }
            representation.setRoles(data);
        }
        if (null != user.getHeadPic()) {
            PictureRepresentation data = mappingService.map(user.getHeadPic(), PictureRepresentation.class, false);
            representation.setHeadPic(data);
        }
        if (null != user.getIdentityCard()) {
            representation.setIdentityCard(user.getIdentityCard().getId());
        }
        if (null != user.getArea()) {
            ApiAreaRepresentation data = mappingService.map(user.getArea(), ApiAreaRepresentation.class, false);
            representation.setArea(data);
        }
        Subject subject = SecurityUtils.getSubject();
        UserRepresentation account = (UserRepresentation) subject.getSession().getAttribute(Constants.SESSION_USER);
        if (null != account) {
            if (null == clickLickService.searchByUser(user.getId(), account.getId())) {
                representation.setClicked(false);
            } else {
                representation.setClicked(true);
            }

            if (null == followService.searchByUser(account.getId(), user.getUserName())) {
                representation.setFollow(false);
            } else {
                representation.setFollow(true);
            }
        }
    }

}
