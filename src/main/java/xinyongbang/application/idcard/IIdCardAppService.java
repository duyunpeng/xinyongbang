package xinyongbang.application.idcard;

import xinyongbang.application.idcard.command.ListIdCardCommand;
import xinyongbang.application.idcard.representation.IdCardRepresentation;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by dyp on 2016/5/23.
 */

public interface IIdCardAppService {

    Pagination<IdCardRepresentation> pagination(ListIdCardCommand command);

    IdCardRepresentation searchByID(String id);

}
