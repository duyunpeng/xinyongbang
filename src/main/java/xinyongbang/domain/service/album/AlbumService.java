package xinyongbang.domain.service.album;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.album.command.CreateAlbumCommand;
import xinyongbang.application.album.command.CreateAlbumPictureCommand;
import xinyongbang.application.album.command.EditAlbumCommand;
import xinyongbang.application.album.command.EditAlbumPictureCommand;
import xinyongbang.application.picture.command.CreatePictureCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.ListUserCommand;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.domain.model.album.Album;
import xinyongbang.domain.model.album.IAlbumRepository;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.picture.IPictureService;
import xinyongbang.domain.service.user.IUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/5/16.
 */
@Service("albumService")
public class AlbumService implements IAlbumService {

    @Autowired
    private IAlbumRepository<Album, String> albumRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired
    private IPictureService pictureService;

    @Override
    public Album searchByID(String id) {
        Album album = albumRepository.getById(id);
        if (null == album) {
            throw new NoFoundException("没有找到ID[" + id + "]的Album数据");
        }
        return album;
    }

    @Override
    public void delete(SharedCommand command) {
        Album album = this.searchByID(command.getId());

        albumRepository.delete(album);
    }


    @Override
    public void apiCreate(CreateAlbumCommand command) {
        User user = userService.searchByID(command.getUser());
        Album album = new Album(user, command.getName(), command.getDescribes(), null);
        albumRepository.save(album);
    }

    @Override
    public List<Album> apiList(String id) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("user.id", id));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return albumRepository.list(criterionList, orderList);
    }

    @Override
    public void apiEdit(EditAlbumCommand command) {
        Album album = this.searchByID(command.getId());

        album.changeName(command.getName());
        album.changeDescribes(command.getDescribes());

        albumRepository.update(album);
    }

    @Override
    public void apiAddAlbumPicture(CreateAlbumPictureCommand command) {
        Album album = this.searchByID(command.getAlbumId());

        List<Picture> pictureList = album.getPictureList();
        for (String picPath : command.getPicPath().split(",")) {
            CreatePictureCommand picCommand = fileUploadService.moveToImg(picPath);
            picCommand.setDescribes(command.getDescribe());
            Picture newHeadPic = pictureService.create(picCommand);
            pictureList.add(newHeadPic);
        }

        album.changePictureList(pictureList);
        albumRepository.update(album);
    }

    @Override
    public void apiDeleteAlbumPicture(EditAlbumPictureCommand command) {
        Album album = this.searchByID(command.getAlbumId());
        List<Picture> pictureList = album.getPictureList();
        List<Picture> removeList = new ArrayList<Picture>();
        for (String comPicPath : command.getPicPath().split(",")) {
            for (Picture picture : pictureList) {
                if (comPicPath.equals(picture.getPicPath())) {
                    removeList.add(picture);
                }
            }
        }

        pictureList.removeAll(removeList);

        album.changePictureList(pictureList);

        albumRepository.update(album);

        for (Picture item : removeList) {
            fileUploadService.deleteImg(item.getPicPath());
        }

    }

    @Override
    public List<Album> apiFindAlbumByUser(ListUserCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("user.userName", command.getUserName()));
        Map<String, String> alias = new HashMap<String, String>();
        alias.put("user", "user");
        return albumRepository.list(criterionList, null, null, null, alias);
    }

}
