package xinyongbang.application.slide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.slide.command.CreateSlideCommand;
import xinyongbang.application.slide.command.EditSlideCommand;
import xinyongbang.application.slide.command.ListSlideCommand;
import xinyongbang.application.slide.representation.SlideRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.slide.Slide;
import xinyongbang.domain.service.slide.ISlideService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dyp on 2016/5/20.
 */
@Service("slideAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class SlideAppService implements ISlideAppService {

    @Autowired
    private ISlideService slideService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<SlideRepresentation> pagination(ListSlideCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Slide> pagination=slideService.pagination(command);
        List<SlideRepresentation> data=mappingService.mapAsList(pagination.getData(),SlideRepresentation.class);
        return new Pagination<SlideRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public SlideRepresentation searchByID(String id) {
        return mappingService.map(slideService.searchByID(id),SlideRepresentation.class,false);
    }

    @Override
    public SlideRepresentation create(CreateSlideCommand command) {
        return mappingService.map(slideService.create(command),SlideRepresentation.class,false);
    }

    @Override
    public SlideRepresentation edit(EditSlideCommand command) {
        return mappingService.map(slideService.edit(command),SlideRepresentation.class,false);
    }
}
