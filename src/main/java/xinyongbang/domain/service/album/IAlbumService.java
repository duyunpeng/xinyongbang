package xinyongbang.domain.service.album;

import xinyongbang.application.album.command.CreateAlbumCommand;
import xinyongbang.application.album.command.CreateAlbumPictureCommand;
import xinyongbang.application.album.command.EditAlbumCommand;
import xinyongbang.application.album.command.EditAlbumPictureCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.ListUserCommand;
import xinyongbang.domain.model.album.Album;

import java.util.List;

/**
 * Created by YJH on 2016/5/16.
 */
public interface IAlbumService {
    Album searchByID(String id);

    void delete(SharedCommand command);


    //api

    void apiCreate(CreateAlbumCommand command);

    List<Album> apiList(String id);

    void apiEdit(EditAlbumCommand command);

    void apiAddAlbumPicture(CreateAlbumPictureCommand command);

    void apiDeleteAlbumPicture(EditAlbumPictureCommand command);

    List<Album> apiFindAlbumByUser(ListUserCommand command);
}
