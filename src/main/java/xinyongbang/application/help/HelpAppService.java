package xinyongbang.application.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import xinyongbang.application.help.command.CreateHelpCommand;
import xinyongbang.application.help.command.EditHelpCommand;
import xinyongbang.application.help.command.ListHelpCommand;
import xinyongbang.application.help.representation.HelpRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.help.Help;
import xinyongbang.domain.service.help.IHelpService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dw on 2016/5/23.
 */
@Service("helpAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class HelpAppService implements IHelpAppService{


    @Autowired
    private IHelpService helpService ;

    @Autowired
    private IMappingService mappingService ;

    @Override
    @Transactional(readOnly = true)
    public Pagination<HelpRepresentation> pagination(ListHelpCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Help> pagination = helpService.pagination(command);
        List<HelpRepresentation> data = mappingService.mapAsList(pagination.getData(), HelpRepresentation.class);
        return new Pagination<HelpRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    public HelpRepresentation create(CreateHelpCommand command) {
        return mappingService.map(helpService.create(command),HelpRepresentation.class,false);
    }

    @Override
    @Transactional(readOnly = true)
    public HelpRepresentation searchByID(String id) {
        return mappingService.map(helpService.searchByID(id),HelpRepresentation.class,false);
    }

    @Override
    public HelpRepresentation edit(EditHelpCommand command) {
        return mappingService.map(helpService.edit(command),HelpRepresentation.class,false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        helpService.updateStatus(command);
    }
}
