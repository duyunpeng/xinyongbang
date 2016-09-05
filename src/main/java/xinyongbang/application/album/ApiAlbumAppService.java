package xinyongbang.application.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.album.command.CreateAlbumCommand;
import xinyongbang.application.album.command.CreateAlbumPictureCommand;
import xinyongbang.application.album.command.EditAlbumCommand;
import xinyongbang.application.album.command.EditAlbumPictureCommand;
import xinyongbang.application.album.representation.ApiAlbumRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.application.user.command.ListUserCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.album.Album;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.service.album.IAlbumService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/5/16.
 */
@Service("apiAlbumAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiAlbumAppService implements IApiAlbumAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IAlbumService albumService;

    @Override
    public ApiResponse create(CreateAlbumCommand command) {
        if (CoreStringUtils.isEmpty(command.getName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "相册名称(name)不能为空", null);
        }
        albumService.apiCreate(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse list(String id) {
        List<ApiAlbumRepresentation> data = mappingService.mapAsList(albumService.apiList(id), ApiAlbumRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse edit(EditAlbumCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "相册id(id)不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "相册名称(name)不能为空", null);
        }
        albumService.apiEdit(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse delete(SharedCommand command) {
        if(CoreStringUtils.isEmpty(command.getId())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "相册id(id)不能为空", null);
        }

        albumService.delete(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse addAlbumPicture(CreateAlbumPictureCommand command) {
        if(CoreStringUtils.isEmpty(command.getAlbumId())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "相册id(id)不能为空", null);
        }
        if(CoreStringUtils.isEmpty(command.getPicPath())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT,"相片路径不存在",null);
        }

        albumService.apiAddAlbumPicture(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse deleteAlbumPicture(EditAlbumPictureCommand command) {
        if(CoreStringUtils.isEmpty(command.getAlbumId())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "相册id(id)不能为空", null);
        }
        if(CoreStringUtils.isEmpty(command.getPicPath())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT,"相片路径不存在",null);
        }

        albumService.apiDeleteAlbumPicture(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    public ApiResponse findAlbumByUser(ListUserCommand command) {
        if(CoreStringUtils.isEmpty(command.getUserName())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "用户名(userName)不能为空", null);
        }
        List<Album> albumList = albumService.apiFindAlbumByUser(command);
        List<ApiAlbumRepresentation> data = mappingService.mapAsList(albumList, ApiAlbumRepresentation.class);
        if(null != albumList && albumList.size() > 0) {
            List<PictureRepresentation> pictureList = new ArrayList<PictureRepresentation>();
            for (PictureRepresentation item : data.get(0).getPictureList()) {
                pictureList.add(item);
                if (pictureList.size() > 3) {
                    break;
                }
            }
            data.get(0).setPictureList(pictureList);
        }

        return new ApiResponse(ApiReturnCode.SUCCESS,ApiReturnCode.SUCCESS.getName(),data);
    }

}
