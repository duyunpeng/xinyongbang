package xinyongbang.application.slide;


import xinyongbang.application.slide.command.CreateSlideCommand;
import xinyongbang.application.slide.command.EditSlideCommand;
import xinyongbang.application.slide.command.ListSlideCommand;
import xinyongbang.application.slide.representation.SlideRepresentation;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by dyp on 2016/5/20.
 */
public interface ISlideAppService {

    Pagination<SlideRepresentation> pagination(ListSlideCommand command);

    SlideRepresentation searchByID(String id);

    SlideRepresentation create(CreateSlideCommand command);

    SlideRepresentation edit(EditSlideCommand command);




}
