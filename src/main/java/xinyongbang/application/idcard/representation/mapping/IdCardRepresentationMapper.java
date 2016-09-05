package xinyongbang.application.idcard.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.idcard.representation.IdCardRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.idcard.IdCard;

/**
 * Created by dyp on 2016/5/23.
 */
@Component
public class IdCardRepresentationMapper extends CustomMapper<IdCard,IdCardRepresentation>{

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(IdCard idCard, IdCardRepresentation representation, MappingContext context){

    }
}
