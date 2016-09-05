package xinyongbang.domain.service.collection;

import xinyongbang.application.collection.command.ListCollectionCommand;
import xinyongbang.application.collection.command.NewCollectionCommand;
import xinyongbang.domain.model.collection.Collection;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by YJH on 2016/5/24.
 */
public interface ICollectionService {
    void apiCreate(NewCollectionCommand command);

    Pagination<Collection> apiPagination(ListCollectionCommand command);
}
