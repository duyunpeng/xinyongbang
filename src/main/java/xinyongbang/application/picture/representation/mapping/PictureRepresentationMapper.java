package xinyongbang.application.picture.representation.mapping;


import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.domain.model.picture.Picture;

/**
 * Created by YJH on 2016/4/12 0012.
 */
@Component
public class PictureRepresentationMapper extends CustomMapper<Picture, PictureRepresentation> {
}
