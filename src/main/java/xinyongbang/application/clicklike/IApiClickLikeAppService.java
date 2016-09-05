package xinyongbang.application.clicklike;

import xinyongbang.application.clicklike.command.ClickLikeCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/29.
 */
public interface IApiClickLikeAppService {
    ApiResponse clickLike(ClickLikeCommand command);

    ApiResponse cancelClickLike(ClickLikeCommand command);

    ApiResponse getClickLikeCount(ClickLikeCommand command);
}
