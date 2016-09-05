package xinyongbang.application.help;

import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by dw on 2016/5/23.
 */
public interface IApiHelpAppService {

    ApiResponse list();

    ApiResponse info(SharedCommand command);
}
