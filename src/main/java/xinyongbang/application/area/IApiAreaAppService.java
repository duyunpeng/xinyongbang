package xinyongbang.application.area;

import xinyongbang.application.area.command.ListAreaCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/17 0017.
 */
public interface IApiAreaAppService {
    ApiResponse searchByParent(ListAreaCommand command);

    ApiResponse searchByID(SharedCommand command);
}
