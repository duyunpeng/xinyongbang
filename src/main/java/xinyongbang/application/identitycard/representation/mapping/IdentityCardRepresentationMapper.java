package xinyongbang.application.identitycard.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.identitycard.representation.IdentityCardRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.identitycard.IdentityCard;

/**
 * Created by dyp on 2016/4/21.
 */
@Component
public class IdentityCardRepresentationMapper extends CustomMapper<IdentityCard, IdentityCardRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(IdentityCard identityCard, IdentityCardRepresentation representation, MappingContext context){
        if(null != identityCard.getFrontPic()){
            PictureRepresentation data = mappingService.map(identityCard.getFrontPic(),PictureRepresentation.class,false);
            representation.setFrontPic(data);
        }

        if(null != identityCard.getBackPic()){
            PictureRepresentation data = mappingService.map(identityCard.getBackPic(),PictureRepresentation.class,false);
            representation.setBackPic(data);
        }
    }

}
