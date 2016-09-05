package xinyongbang.application.collection;

import xinyongbang.application.collection.command.ListCollectionCommand;
import xinyongbang.application.collection.command.NewCollectionCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/5/24.
 */
public interface IApiCollectionAppService {
    ApiResponse newCollection(NewCollectionCommand command);

    ApiResponse list(ListCollectionCommand command);
}
