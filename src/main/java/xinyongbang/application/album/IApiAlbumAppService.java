package xinyongbang.application.album;

import xinyongbang.application.album.command.CreateAlbumCommand;
import xinyongbang.application.album.command.CreateAlbumPictureCommand;
import xinyongbang.application.album.command.EditAlbumCommand;
import xinyongbang.application.album.command.EditAlbumPictureCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.ListUserCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/5/16.
 */
public interface IApiAlbumAppService {
    ApiResponse create(CreateAlbumCommand command);

    ApiResponse list(String id);

    ApiResponse edit(EditAlbumCommand command);

    ApiResponse delete (SharedCommand command);

    ApiResponse addAlbumPicture(CreateAlbumPictureCommand command);

    ApiResponse deleteAlbumPicture(EditAlbumPictureCommand command);

    ApiResponse findAlbumByUser(ListUserCommand command);
}
