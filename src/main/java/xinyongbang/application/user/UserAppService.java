package xinyongbang.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.CreateUserCommand;
import xinyongbang.application.user.command.EditUserCommand;
import xinyongbang.application.user.command.ListUserCommand;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/19.
 */
@Service("userAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserAppService implements IUserAppService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<UserRepresentation> pagination(ListUserCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<User> pagination = userService.pagination(command);
        List<UserRepresentation> data = mappingService.mapAsList(pagination.getData(), UserRepresentation.class);
        return new Pagination<UserRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public UserRepresentation searchByID(String id) {
        return mappingService.map(userService.searchByID(id), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation create(CreateUserCommand command) {
        return mappingService.map(userService.create(command), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation edit(EditUserCommand command) {
        return mappingService.map(userService.edit(command), UserRepresentation.class, false);
    }

    @Override
    public Pagination<UserRepresentation> userApprove(ListUserCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<User> pagination = userService.apiUserApprove(command);
        List<UserRepresentation> data = mappingService.mapAsList(pagination.getData(), UserRepresentation.class);
        return new Pagination<UserRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public UserRepresentation approvePass(SharedCommand command) {
        return mappingService.map(userService.approvePass(command), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation approveReject(SharedCommand command) {
        return mappingService.map(userService.apiApproveReject(command), UserRepresentation.class, false);

    }

    @Override
    public void updateCredit() {
        userService.updateCredit();
    }

    @Override
    public void shareAddGold(String id) {
        userService.shareAddGold(id);
    }
}
