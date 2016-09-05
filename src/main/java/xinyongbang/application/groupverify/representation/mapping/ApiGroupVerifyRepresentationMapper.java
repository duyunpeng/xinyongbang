package xinyongbang.application.groupverify.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import xinyongbang.application.groupverify.representation.ApiGroupVerifyRepresentation;
import xinyongbang.domain.model.groupverify.GroupVerify;

/**
 * Created by YJH on 2016/5/26.
 */
@Component
public class ApiGroupVerifyRepresentationMapper extends CustomMapper<GroupVerify, ApiGroupVerifyRepresentation> {

    @Override
    public void mapAtoB(GroupVerify groupVerify, ApiGroupVerifyRepresentation representation, MappingContext context) {
        if (null != groupVerify.getGroup()) {
            representation.setGroup(groupVerify.getGroup().getId());
            representation.setGroup(groupVerify.getGroup().getName());
        }
        if (null != groupVerify.getApplicantUser()) {
            representation.setApplicantUser(groupVerify.getApplicantUser().getId());
            representation.setApplicantUserName(groupVerify.getApplicantUser().getUserName());
        }
    }
}
