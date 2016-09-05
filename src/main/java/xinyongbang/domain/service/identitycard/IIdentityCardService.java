package xinyongbang.domain.service.identitycard;


import xinyongbang.application.identitycard.command.*;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.api.command.IdentityCardVerify;
import xinyongbang.domain.model.identitycard.IdentityCard;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dyp on 2016/4/20.
 */
public interface IIdentityCardService {
    Pagination<IdentityCard> pagination(ListIdentityCardCommand command);

    IdentityCard searchByID(String id);

    IdentityCard create(CreateIdentityCardCommand command);

    IdentityCard edit(EditIdentityCardCommand command);

    void updateStatus(SharedCommand command);

    List<IdentityCard> list(ListIdentityCardCommand command);

    //Api
    void apiAttestation(AttestationIdentityCardCommand command);
}
