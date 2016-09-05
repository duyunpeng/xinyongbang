package xinyongbang.application.account.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.account.representation.ApiAccountRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.account.Account;
import xinyongbang.domain.model.role.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/5/18.
 */
@Component
public class ApiAccountRepresentationMapper extends CustomMapper<Account, ApiAccountRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Account account, ApiAccountRepresentation representation, MappingContext context) {
        if (null != account.getAppKey()) {
            representation.setAppKey(account.getAppKey().getId());
        }
        if (null != account.getRoles()) {
            List<String> data = new ArrayList<String>();
            for (Role role : account.getRoles()){
                data.add(role.getId());
            }
            representation.setRoles(data);
        }
        if (null != account.getHeadPic()) {
            PictureRepresentation data = mappingService.map(account.getHeadPic(), PictureRepresentation.class, false);
            representation.setHeadPic(data);
        }
    }

}
