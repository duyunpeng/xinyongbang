package xinyongbang.domain.service.slide;

import xinyongbang.application.slide.command.CreateSlideCommand;
import xinyongbang.application.slide.command.EditSlideCommand;
import xinyongbang.application.slide.command.ListSlideCommand;
import xinyongbang.domain.model.slide.Slide;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 2016/5/20.
 */
public interface ISlideService {

    Pagination<Slide> pagination(ListSlideCommand command);

    Slide searchByID(String id);

    Slide create(CreateSlideCommand command);

    Slide edit(EditSlideCommand command);

    /***Api接口**/

    List<Slide> allList();


}
