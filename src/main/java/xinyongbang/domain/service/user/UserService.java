package xinyongbang.domain.service.user;

import org.hibernate.LockOptions;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.account.command.ResetPasswordCommand;
import xinyongbang.application.album.command.CreateAlbumCommand;
import xinyongbang.application.clicklike.command.ClickLikeCommand;
import xinyongbang.application.golddetailed.command.CreateGoldDetailedCommand;
import xinyongbang.application.picture.command.CreatePictureCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.*;
import xinyongbang.core.common.Constants;
import xinyongbang.core.common.PasswordHelper;
import xinyongbang.core.enums.AuthStatus;
import xinyongbang.core.enums.EnableStatus;
import xinyongbang.core.enums.FlowType;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.core.util.calculation.CoreCalculationUtils;
import xinyongbang.core.util.calculation.Degree;
import xinyongbang.domain.model.appkey.AppKey;
import xinyongbang.domain.model.area.Area;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.role.Role;
import xinyongbang.domain.model.user.IUserRepository;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.account.IAccountService;
import xinyongbang.domain.service.album.IAlbumService;
import xinyongbang.domain.service.appkey.IAppKeyService;
import xinyongbang.domain.service.area.IAreaService;
import xinyongbang.domain.service.clicklike.IClickLickService;
import xinyongbang.domain.service.golddetailed.IGoldDetailedService;
import xinyongbang.domain.service.picture.IPictureService;
import xinyongbang.domain.service.role.IRoleService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/4/19.
 */
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserRepository<User, String> userRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IAppKeyService appKeyService;

    @Autowired
    private IGoldDetailedService goldDetailedService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private IClickLickService clickLickService;

    @Autowired
    private IAlbumService albumService;

    @Override
    public Pagination<User> pagination(ListUserCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        List<Order> orderList = new ArrayList<Order>();

        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (null != command.getAuthStatus() && command.getAuthStatus() != AuthStatus.ALL) {
            criterionList.add(Restrictions.eq("authStatus", command.getAuthStatus()));
        }
        orderList.add(Order.asc("createDate"));
        return userRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public User searchByName(String userName) {
        return userRepository.searchByName(userName);
    }

    @Override
    public User searchByID(String id) {
        User user = userRepository.getById(id);
        if (null == user) {
            throw new NoFoundException("没有找到ID[" + id + "]的User数据");
        }
        return user;
    }

    @Override
    public User searchByID(String id, LockOptions lockOptions) {
        User user = userRepository.getById(id, lockOptions);
        if (null == user) {
            throw new NoFoundException("没有找到ID[" + id + "]的User数据");
        }
        return user;
    }

    @Override
    public User create(CreateUserCommand command) {
        Area area = null;
        if (!CoreStringUtils.isEmpty(command.getArea())) {
            area = areaService.searchByID(command.getArea());
        }
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        List<Role> roleList = roleService.searchByIDs(command.getRoles());
        if (null != accountService.searchByAccountName(command.getUserName(), appKey.getName())) {
            throw new ExistException("userName[" + command.getUserName() + "]的Account数据已存在");
        }
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), salt);
        User user = new User(command.getUserName(), password, salt, null, null, null, roleList, command.getEmail(),
                appKey, command.getStatus(), null, command.getName(), area, null, new BigDecimal(0), AuthStatus.NOT, null, null);
        userRepository.save(user);
        return user;
    }

    @Override
    public User edit(EditUserCommand command) {
        User user = this.searchByID(command.getId());
        user.fainWhenConcurrencyViolation(command.getVersion());
        Area area = null;
        if (!CoreStringUtils.isEmpty(command.getArea())) {
            area = areaService.searchByID(command.getArea());
        }
        user.changeName(command.getName());
        user.changeArea(area);
        user.changeEmail(command.getEmail());
        userRepository.update(user);
        return user;
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }


    @Override
    public User approvePass(SharedCommand command) {
        User user = userRepository.getById(command.getId());
        user.fainWhenConcurrencyViolation(command.getVersion());
        user.changeName(user.getIdentityCard().getName());
        user.changeAuthStatus(AuthStatus.SUCCESS);
        userRepository.update(user);
        return user;
    }

    @Override
    public void updateCredit() {
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("credit"));
        List<User> userList = userRepository.list(null, orderList);
        for (int i = userList.size() - 1; i >= 0; i--) {
            ClickLikeCommand command = new ClickLikeCommand();
            command.setUser(userList.get(i).getId());
            userList.get(i).changeCredit(clickLickService.list(command).size());
            userList.get(i).changeRanking(i + 1);
            userRepository.update(userList.get(i));
        }
    }

    @Override
    public void shareAddGold(String id) {
        User user = this.searchByID(id, LockOptions.UPGRADE);
        user.changeGold(user.getGold().add(new BigDecimal(1)));
        userRepository.update(user);

        //创建金币明细
        CreateGoldDetailedCommand command = new CreateGoldDetailedCommand();
        command.setUser(user.getId());
        command.setDescription("分享加金币");
        command.setFlowType(FlowType.IN_FLOW);
        command.setGoldNumber(new BigDecimal(1));
        goldDetailedService.create(command);
    }

    @Override
    public void apiCreate(RegisterCommand command) {
        if (null != this.searchByName(command.getUserName())) {
            throw new ExistException("userName[" + command.getUserName() + "]的User数据已存在");
        }
        List<Role> roles = new ArrayList<Role>();
        AppKey appKey = appKeyService.searchByName(Constants.APP_KRY);
        Role role = roleService.searchByName("user", Constants.APP_KRY);
        roles.add(role);
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), salt);

        //默认头像
        Picture picture = null;
        picture = pictureService.searchByDescribes("默认用户头像图片");
        if (null == picture) {
            CreatePictureCommand pictureCommand = new CreatePictureCommand();
            pictureCommand.setDescribes("默认用户头像图片");
            String iconPath = fileUploadService.getDoMainName() + "/resources/images/default_user_head.png";
            pictureCommand.setName("default_user_head.png");
            pictureCommand.setPicPath(iconPath);
            pictureCommand.setMediumPicPath(iconPath);
            pictureCommand.setMiniPicPath(iconPath);
            pictureCommand.setSize(0.0);
            picture = pictureService.create(pictureCommand);
        }
        User user = new User(command.getUserName(), password, salt, roles, appKey, picture);
        userRepository.save(user);
        CreateAlbumCommand albumCommand = new CreateAlbumCommand();
        albumCommand.setUser(user.getId());
        albumCommand.setName("用户相册");
        albumCommand.setName("用户相册描述");
        albumService.apiCreate(albumCommand);
    }

    @Override
    public void apiResetPassword(ResetPasswordCommand command) {
        User user = this.searchByName(command.getUserName());
        if (null == user) {
            throw new NoFoundException("账户不存在");
        }

        String password = PasswordHelper.encryptPassword(command.getPassword(), user.getSalt());
        user.changePassword(password);
        userRepository.update(user);
    }

    @Override
    public void apiResetPayPassword(ResetPasswordCommand command) {
        User user = this.searchByName(command.getUserName());
        if (null == user) {
            throw new NoFoundException("账户不存在");
        }
        String password = PasswordHelper.encryptPassword(command.getPassword(), user.getSalt());
        user.changePayPassword(password);
        userRepository.update(user);
    }

    @Override
    public void apiUpdateHeadPic(EditUserCommand command) {
        User user = this.searchByID(command.getId());
        Picture oldHeadPic = user.getHeadPic();

        user.changeHeadPic(null);

        CreatePictureCommand picCommand = fileUploadService.moveToImg(command.getHeadPic());
        picCommand.setDescribes("头像图片");
        Picture newHeadPic = pictureService.create(picCommand);

        user.changeHeadPic(newHeadPic);
        userRepository.update(user);

        if (null != oldHeadPic && !oldHeadPic.getDescribes().equals("默认用户头像图片")) {
            fileUploadService.deleteImg(oldHeadPic.getName());
            pictureService.delete(oldHeadPic.getId());
        }
    }

    @Override
    public void apiUpdateInfo(EditUserCommand command) {
        User user = this.searchByID(command.getId());
        Area area = areaService.searchByID(command.getArea());
        user.changeArea(area);
        user.changeJobDescription(command.getJobDescription());
//        user.changeName(command.getName());
        userRepository.update(user);
    }

    @Override
    public void apiFlushLocation(FlushLocationCommand command) {
        User user = this.searchByID(command.getUser(), LockOptions.UPGRADE);
        user.changeLongitude(command.getLongitude());
        user.changeLatitude(command.getLatitude());
        userRepository.update(user);
    }

    @Override
    public Pagination<User> apiPagination(ListUserCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getArea())) {
            aliasMap.put("area", "area");
            aliasMap.put("area.parent", "parent");
            aliasMap.put("area.parent.parent", "parent.parent");
            criterionList.add(Restrictions.or(Restrictions.eq("area.id", command.getArea()),
                    Restrictions.eq("parent.id", command.getArea()),
                    Restrictions.eq("parent.parent.id", command.getArea())));
        }
        if (null != command.getDistance()) {
            Degree[] dd = CoreCalculationUtils.GetDegreeCoordinates(new Degree(command.getLongitude(), command.getLatitude()), command.getDistance() * 1000);
            criterionList.add(Restrictions.between("latitude", dd[3].getLatitude(), dd[0].getLatitude()));
            criterionList.add(Restrictions.between("longitude", dd[3].getLongitude(), dd[0].getLongitude()));
        }
        if (!CoreStringUtils.isEmpty(command.getUser())) {
            criterionList.add(Restrictions.ne("id", command.getUser()));
        }
        if (null != command.getAuthStatus()) {
            criterionList.add(Restrictions.eq("authStatus", command.getAuthStatus()));
        }

        orderList.add(Order.asc("ranking"));

        return userRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public List<User> apiFindFriend(ListUserCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));

        return userRepository.list(criterionList, null);
    }

    @Override
    public Pagination<User> apiUserApprove(ListUserCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("authStatus", AuthStatus.IN));
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return userRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public User apiApproveReject(SharedCommand command) {
        User user = userRepository.getById(command.getId());
        user.fainWhenConcurrencyViolation(command.getVersion());
        user.changeAuthStatus(AuthStatus.FAILURE);
        userRepository.update(user);
        return user;
    }

    @Override
    public List<User> apiContactsList(ListUserCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.in("userName", command.getContactsUserList()));
        if (CoreStringUtils.isEmpty(command.getUser())) {
            criterionList.add(Restrictions.ne("id", command.getUser()));
        }
        return userRepository.list(criterionList, null);
    }

    @Override
    public void apiChangeUserName(ChangeUserNameCommand command) {
        User user = this.searchByID(command.getUser(), LockOptions.UPGRADE);
        user.changeUserName(command.getNewUserName());
        userRepository.update(user);
    }
}
