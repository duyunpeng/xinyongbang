package xinyongbang.domain.service.clicklike;

import xinyongbang.application.clicklike.command.ClickLikeCommand;
import xinyongbang.domain.model.clicklike.ClickLike;

import java.util.List;

/**
 * Created by YJH on 2016/4/29.
 */
public interface IClickLickService {
    ClickLike searchByUser(String user, String clickUser);

    void apiClickLike(ClickLikeCommand command);

    void apiCancelClickLike(ClickLikeCommand command);

    List<ClickLike> list(ClickLikeCommand command);
}
