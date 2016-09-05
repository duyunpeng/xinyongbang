package xinyongbang.application.idcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.idcard.command.ListIdCardCommand;
import xinyongbang.application.idcard.representation.IdCardRepresentation;
import xinyongbang.application.identitycard.representation.IdentityCardRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.idcard.IdCard;
import xinyongbang.domain.service.idcard.IIdCardService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 2016/5/23.
 */
@Service("idCardAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class IdCardAppService implements IIdCardAppService{

    @Autowired
    private IIdCardService idCardService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<IdCardRepresentation> pagination(ListIdCardCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<IdCard> pagination = idCardService.pagination(command);
        List<IdCardRepresentation> data = mappingService.mapAsList(pagination.getData(), IdCardRepresentation.class);
        return new Pagination<IdCardRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public IdCardRepresentation searchByID(String id) {
        return mappingService.map(idCardService.searchByID(id),IdCardRepresentation.class,false);
    }
}
