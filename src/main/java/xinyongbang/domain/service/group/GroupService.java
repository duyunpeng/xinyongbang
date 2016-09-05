package xinyongbang.domain.service.group;

import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.group.command.EditGroupCommand;
import xinyongbang.application.group.command.ListGroupCommand;
import xinyongbang.application.group.command.NewGroupCommand;
import xinyongbang.application.group.representation.ApiGroupRepresentation;
import xinyongbang.application.picture.command.CreatePictureCommand;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.exception.NotAdminException;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.core.util.CoreImgUtils;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.group.Group;
import xinyongbang.domain.model.group.IGroupRepository;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.picture.IPictureService;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jm on 16-5-19.
 */
@Service("groupService")
public class GroupService implements IGroupService {

    @Autowired
    private IGroupRepository<Group, String> groupRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    public List<Group> apiGroupList(ListGroupCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
//        criterionList.add(Restrictions.eq("user.id", command.getUser()));
        criterionList.add(Restrictions.or(Restrictions.eq("user.id", command.getUser()),
                Restrictions.in("userList.id", new String[]{command.getUser()})));
        Map<String, String> alias = new HashMap<String, String>();
        alias.put("userList", "userList");
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return groupRepository.list(criterionList, orderList, null, null, alias);
    }

    @Override
    public Group searchByID(String id) {
        Group group = groupRepository.getById(id);
        if (null == group) {
            throw new NoFoundException("没有找到ID[" + id + "]的Group数据");
        }
        return group;
    }

    @Override
    public void update(Group group) {
        groupRepository.update(group);
    }

    @Override
    public void apiCreate(NewGroupCommand command) {
        User user = userService.searchByID(command.getUser());
        String groupNo = this.getGroupNo();
        Picture picture = null;
        if (CoreStringUtils.isEmpty(command.getPicture())) {
            picture = pictureService.searchByDescribes("默认群头像图片");
            if (null == picture) {
                CreatePictureCommand pictureCommand = new CreatePictureCommand();
                pictureCommand.setDescribes("默认群头像图片");
                String iconPath = fileUploadService.getDoMainName() + "/resources/images/default_group_head.png";
                pictureCommand.setName("default_group_head.png");
                pictureCommand.setPicPath(iconPath);
                pictureCommand.setMediumPicPath(iconPath);
                pictureCommand.setMiniPicPath(iconPath);
                pictureCommand.setSize(0.0);
                picture = pictureService.create(pictureCommand);
            }
        } else {
            CreatePictureCommand pictureCommand = fileUploadService.moveToImg(command.getPicture());
            pictureCommand.setDescribes("群头像图片");
            picture = pictureService.create(pictureCommand);
        }
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        Group group = new Group(groupNo, user, command.getGroupName(), userList, command.getDescription(), picture);
        groupRepository.save(group);
    }

    @Override
    public Group searchByNO(String groupNo) {
        return groupRepository.searchByNO(groupNo);
    }

    @Override
    public List<Group> apiFindGroup(ListGroupCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.like("groupNo", command.getGroupNo(), MatchMode.ANYWHERE));
        return groupRepository.list(criterionList, null);
    }

    @Override
    public void apiDeleteGroupUser(ListGroupCommand command) {
        Group group = this.searchByID(command.getGroup());
        if (!group.getUser().getId().equals(command.getUser())) {
            throw new NotAdminException("不是群管理员");
        }
        List<User> userList = group.getUserList();
        for (User item : userList) {
            for (String item_id : command.getUserId()) {
                if (item.getId().equals(item_id)) {
                    userList.remove(item);
                    break;
                }
            }
        }
        group.changeUserList(userList);
        groupRepository.update(group);
    }

    @Override
    public List<Group> apiPromotion() {
        List<Group> groupList = groupRepository.findAll();
        Map<Integer, Group> data = new HashMap<Integer, Group>();
        for (int i = 0; i < 3; i++) {
            int index = (int) (Math.random() * groupList.size());
            data.put(index, groupList.get(index));
        }
        groupList = new ArrayList<Group>();
        for (Group item : data.values()) {
            groupList.add(item);
        }
        return groupList;
    }

    @Override
    public void apiUpdateInfo(EditGroupCommand command) {
        User user = userService.searchByID(command.getUser());
        Group group = this.searchByID(command.getId());
        if (!user.getId().equals(group.getUser().getId())) {
            throw new NotAdminException("不是群管理员");
        }

        group.changeDescription(command.getDescription());
        group.changeName(command.getGroupName());

        Picture oldPicture = group.getPicture();
        if (!oldPicture.getPicPath().equals(command.getPicture())) {
            CreatePictureCommand pictureCommand = fileUploadService.moveToImg(command.getPicture());
            Picture picture = pictureService.create(pictureCommand);
            group.changePicture(picture);
        }

        groupRepository.update(group);

        if (null != oldPicture && !oldPicture.getDescribes().equals("默认群头像图片")) {
            fileUploadService.deleteImg(oldPicture.getName());
            pictureService.delete(oldPicture.getId());
        }

    }

    /**
     * 生成群号
     *
     * @return
     */
    private String getGroupNo() {
        String groupNo = CoreStringUtils.randomNum(9);
        if (null != this.searchByNO(groupNo)) {
            groupNo = getGroupNo();
        }
        return groupNo;
    }

}
