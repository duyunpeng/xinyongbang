package xinyongbang.application.album.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.album.representation.AlbumRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.album.Album;

import java.util.List;

/**
 * Created by YJH on 2016/5/16.
 */
@Component
public class AlbumRepresentationMapper extends CustomMapper<Album, AlbumRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Album album, AlbumRepresentation representation, MappingContext context) {
        if (null != album.getUser()) {
            UserRepresentation data = mappingService.map(album.getUser(),UserRepresentation.class,false);
            representation.setUser(data);
        }
        if (null != album.getPictureList()) {
            List<PictureRepresentation> data = mappingService.mapAsList(album.getPictureList(), PictureRepresentation.class);
            representation.setPictureList(data);
        }
    }

}
