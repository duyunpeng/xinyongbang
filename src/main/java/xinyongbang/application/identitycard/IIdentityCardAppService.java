package xinyongbang.application.identitycard;


import xinyongbang.application.identitycard.command.CreateIdentityCardCommand;
import xinyongbang.application.identitycard.command.EditIdentityCardCommand;
import xinyongbang.application.identitycard.command.IdentityCardVerifyCommand;
import xinyongbang.application.identitycard.command.ListIdentityCardCommand;
import xinyongbang.application.identitycard.representation.IdentityCardRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.identitycard.IdentityCard;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 2016/4/20.
 */
public interface IIdentityCardAppService {
    Pagination<IdentityCardRepresentation> pagination(ListIdentityCardCommand command);

    IdentityCardRepresentation searchByID(String id);

    IdentityCardRepresentation create(CreateIdentityCardCommand command);

    IdentityCardRepresentation edit(EditIdentityCardCommand command);

    void updateStatus(SharedCommand command);

    List<IdentityCardRepresentation> listJSON(ListIdentityCardCommand command);

}
