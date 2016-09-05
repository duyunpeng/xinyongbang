package xinyongbang.application.feedback.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.feedback.representation.FeedBackRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.feedback.FeedBack;

import java.util.List;


/**
 * Created by Administrator on 2016/4/21.
 */
@Component
public class FeedBackRepresentationMapper extends CustomMapper<FeedBack, FeedBackRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(FeedBack feedBack, FeedBackRepresentation representation, MappingContext context) {
        if (null != feedBack.getPictureList()) {
            List<PictureRepresentation> data = mappingService.mapAsList(feedBack.getPictureList(), PictureRepresentation.class);
            representation.setPictures(data);
        }
    }

}
