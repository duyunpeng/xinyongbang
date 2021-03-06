package xinyongbang.domain.service.account;


import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.account.command.*;
import xinyongbang.application.auth.command.LoginCommand;
import xinyongbang.application.picture.command.CreatePictureCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.common.Constants;
import xinyongbang.core.common.PasswordHelper;
import xinyongbang.core.enums.EnableStatus;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.shiro.UsernamePasswordAppKeyToken;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.account.Account;
import xinyongbang.domain.model.account.IAccountRepository;
import xinyongbang.domain.model.appkey.AppKey;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.role.Role;
import xinyongbang.domain.service.appkey.IAppKeyService;
import xinyongbang.domain.service.picture.IPictureService;
import xinyongbang.domain.service.role.IRoleService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.io.File;
import java.util.*;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("accountService")
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository<Account, String> accountRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAppKeyService appKeyService;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    public Pagination<Account> pagination(ListAccountCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getAppKey())) {
            criterionList.add(Restrictions.eq("appKey.id", command.getAppKey()));
        }
        if (null != command.getStatus() && command.getStatus() != EnableStatus.ALL) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return accountRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public List<Account> list(ListAccountCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return accountRepository.list(criterionList, orderList);
    }

    @Override
    public Account searchByID(String id) {
        Account account = accountRepository.getById(id);
        if (null == account) {
            throw new NoFoundException("没有找到ID[" + id + "]的Account数据");
        }
        return account;
    }

    @Override
    public Account searchByAccountName(String userName, String appKey) {
        return accountRepository.searchByAccountName(userName, appKey);
    }

    @Override
    public Account create(CreateAccountCommand command) {
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        List<Role> roleList = roleService.searchByIDs(command.getRoles());
        if (null != this.searchByAccountName(command.getUserName(), command.getAppKey())) {
            throw new ExistException("userName[" + command.getUserName() + "]的Account数据已存在");
        }
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), salt);
        Account account = new Account(command.getUserName(), password, salt, null, null, null, roleList, command.getEmail(),
                appKey, command.getStatus(), null);
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account edit(EditAccountCommand command) {
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        account.changeEmail(command.getEmail());
        accountRepository.update(account);
        return account;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        if (account.getStatus() == EnableStatus.DISABLE) {
            account.changeStatus(EnableStatus.ENABLE);
        } else {
            account.changeStatus(EnableStatus.DISABLE);
        }
        accountRepository.update(account);
    }

    @Override
    public void resetPassword(ResetPasswordCommand command) {
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        String password = PasswordHelper.encryptPassword(command.getPassword(), account.getSalt());
        account.changePassword(password);
        accountRepository.update(account);
    }

    @Override
    public void authorized(AuthorizeAccountCommand command) {
        List<Role> roleList = roleService.searchByIDs(command.getRoles());
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        account.changeRoles(roleList);
        accountRepository.update(account);
    }

    @Override
    public void updateAppKey(UpdateUserAppKeyCommand command) {
        AppKey appKey = appKeyService.searchByID(command.getAppKey());
        Account account = this.searchByID(command.getId());
        account.fainWhenConcurrencyViolation(command.getVersion());
        account.changeAppKey(appKey);
        accountRepository.update(account);
    }

    @Override
    public Account login(LoginCommand command) {
        Account account = this.searchByAccountName(command.getUserName(), Constants.APP_KRY);
        if (null == account) {
            throw new UnknownAccountException();
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordAppKeyToken token = new UsernamePasswordAppKeyToken(
                command.getUserName(),
                command.getPassword(),
                Constants.APP_KRY);
        subject.login(token);

        account.changeLastLoginIP(command.getLoginIP());
        account.changeLastLoginPlatform(command.getLoginPlatform());
        account.changeLastLoginDate(new Date());
        accountRepository.update(account);

        return account;
    }

    @Override
    public List<Account> searchByIDs(List<String> ids) {
        List<Account> accountList = new ArrayList<Account>();
        for (String item : ids) {
            accountList.add(this.searchByID(item));
        }
        return accountList;
    }

    @Override
    public List<Account> searchByRoleIDs(List<String> ids) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.in("role.id", ids));
        Map<String, String> alias = new HashMap<String, String>();
        alias.put("roles", "role");
        return accountRepository.list(criterionList, null, null, null, alias);
    }

    @Override
    public void updateHeadPic(UpdateHeadPicCommand command) {
        Account account = this.searchByID(command.getId());
        Picture oldHeadPic = account.getHeadPic();

        CreatePictureCommand picCommand = fileUploadService.moveToImg(command.getHandPic());
        picCommand.setDescribes("头像图片");
        Picture newHeadPic = pictureService.create(picCommand);

        account.changeHeadPic(newHeadPic);
        accountRepository.update(account);

        if (null != oldHeadPic) {
            fileUploadService.deleteImg(oldHeadPic.getName());
            pictureService.delete(oldHeadPic.getId());
        }
    }
}
