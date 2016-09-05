package xinyongbang.application.identitycard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.identitycard.command.CreateIdentityCardCommand;
import xinyongbang.application.identitycard.command.EditIdentityCardCommand;
import xinyongbang.application.identitycard.command.ListIdentityCardCommand;
import xinyongbang.application.identitycard.representation.IdentityCardRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.identitycard.IdentityCard;
import xinyongbang.domain.service.identitycard.IIdentityCardService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 2016/4/20.
 */
@Service("identityCardAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class IdentityCardAppService implements IIdentityCardAppService {

    @Autowired
    private IIdentityCardService identityCardService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<IdentityCardRepresentation> pagination(ListIdentityCardCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<IdentityCard> pagination = identityCardService.pagination(command);
        List<IdentityCardRepresentation> data = mappingService.mapAsList(pagination.getData(), IdentityCardRepresentation.class);
        return new Pagination<IdentityCardRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }


    @Override
    @Transactional(readOnly = true)
    public IdentityCardRepresentation searchByID(String id) {
        return mappingService.map(identityCardService.searchByID(id), IdentityCardRepresentation.class, false);
    }


    @Override
    public IdentityCardRepresentation create(CreateIdentityCardCommand command) {
        return mappingService.map(identityCardService.create(command), IdentityCardRepresentation.class, false);
    }

    @Override
    public IdentityCardRepresentation edit(EditIdentityCardCommand command) {
        return mappingService.map(identityCardService.edit(command), IdentityCardRepresentation.class, false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        identityCardService.updateStatus(command);
    }

    @Override
    public List<IdentityCardRepresentation> listJSON(ListIdentityCardCommand command) {
        return mappingService.mapAsList(identityCardService.list(command), IdentityCardRepresentation.class);
    }

}
