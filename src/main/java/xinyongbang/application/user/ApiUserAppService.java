package xinyongbang.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.account.command.ResetPasswordCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.*;
import xinyongbang.application.user.representation.ApiUserRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.enums.AuthStatus;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.redis.RedisService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/28.
 */
@Service("apiUserAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiUserAppService implements IApiUserAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisService redisService;

    @Override
    public ApiResponse register(RegisterCommand command) {
        if (CoreStringUtils.isEmpty(command.getUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "userName字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getPassword())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "password字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getVerificationCode())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "verificationCode字段不能为空", null);
        }
        if (redisService.exists(command.getUserName())) {
            if (command.getVerificationCode().equals(redisService.getCache(command.getUserName()))) {
                userService.apiCreate(command);
                redisService.delete(command.getUserName());
                return new ApiResponse(ApiReturnCode.SUCCESS);
            } else {
                return new ApiResponse(ApiReturnCode.ERROR_CODE_NOT_EQ);
            }
        } else {
            return new ApiResponse(ApiReturnCode.ERROR_NO_CODE);
        }
    }

    @Override
    public ApiResponse info(SharedCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "id字段不能为空", null);
        }
        ApiUserRepresentation data = mappingService.map(userService.searchByID(command.getId()), ApiUserRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse resetPassword(ResetPasswordCommand command) {
        if (CoreStringUtils.isEmpty(command.getUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "用户名(userName)不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getPassword())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "密码(password)不能为空", null);
        }
        userService.apiResetPassword(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse resetPayPassword(ResetPasswordCommand command) {
        if (CoreStringUtils.isEmpty(command.getUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "用户名(userName)不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getPassword())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "支付密码(password)不能为空", null);
        }
        userService.apiResetPayPassword(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse friendInfo(ListUserCommand command) {
        if (CoreStringUtils.isEmpty(command.getUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "用户名(userName)不能为空", null);
        }
        User user = userService.searchByName(command.getUserName());
        if (null == user) {
            return new ApiResponse(ApiReturnCode.ERROR_NO_ACCOUNT);
        }
        ApiUserRepresentation data = mappingService.map(user, ApiUserRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse updateHeadPic(EditUserCommand command) {
        if (CoreStringUtils.isEmpty(command.getHeadPic())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "头像(headPic)不能为空", null);
        }
        userService.apiUpdateHeadPic(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse updateInfo(EditUserCommand command) {
        userService.apiUpdateInfo(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse flushLocation(FlushLocationCommand command) {
        if (null == command.getLatitude()) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "纬度(latitude)字段不能为空", null);
        }
        if (null == command.getLongitude()) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "经度(longitude)字段不能为空", null);
        }
        userService.apiFlushLocation(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse indexPagination(ListUserCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        if (null != command.getDistance() && 0 != command.getDistance()) {
            if (null == command.getLongitude() || null == command.getLatitude()) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "经纬度不能为空", null);
            }
        }
        command.setAuthStatus(AuthStatus.SUCCESS);
        Pagination<User> pagination = userService.apiPagination(command);
        List<ApiUserRepresentation> data = mappingService.mapAsList(pagination.getData(), ApiUserRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(),
                new Pagination<ApiUserRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()));
    }

    @Override
    public ApiResponse findFriend(ListUserCommand command) {
        if (CoreStringUtils.isEmpty(command.getUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "userName字段不能为空", null);
        }
        List<ApiUserRepresentation> data = mappingService.mapAsList(userService.apiFindFriend(command), ApiUserRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse contactsList(ListUserCommand command) {
        if (null == command.getContactsUserList()) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "联系人集合contactsUserList字段不能为空", null);
        }
        List<ApiUserRepresentation> data = mappingService.mapAsList(userService.apiContactsList(command), ApiUserRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse changeUserName(ChangeUserNameCommand command) {
        if (CoreStringUtils.isEmpty(command.getNewUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "新手机账号(newUserName)字段不能为空", null);
        }
        userService.apiChangeUserName(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }
}
