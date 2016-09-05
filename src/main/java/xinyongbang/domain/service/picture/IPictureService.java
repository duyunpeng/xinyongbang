package xinyongbang.domain.service.picture;


import xinyongbang.application.picture.command.CreatePictureCommand;
import xinyongbang.domain.model.picture.Picture;

/**
 * Created by YJH on 2016/4/12.
 */
public interface IPictureService {
    Picture create(CreatePictureCommand command);

    Picture searchByID(String id);

    void delete(String id);

    Picture searchByDescribes(String describes);
}
