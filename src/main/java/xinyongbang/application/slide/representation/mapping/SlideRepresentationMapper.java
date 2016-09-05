package xinyongbang.application.slide.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.slide.representation.SlideRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.slide.Slide;

/**
 * Created by dyp on 2016/5/20.
 */
@Component
public class SlideRepresentationMapper extends CustomMapper<Slide, SlideRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Slide slide, SlideRepresentation representation, MappingContext context) {
        if (null != slide.getPicture()) {
            PictureRepresentation data = mappingService.map(slide.getPicture(), PictureRepresentation.class, false);
            representation.setPicture(data);
        }
    }
}
