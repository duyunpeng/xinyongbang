package xinyongbang.application.help;

import xinyongbang.application.help.command.CreateHelpCommand;
import xinyongbang.application.help.command.EditHelpCommand;
import xinyongbang.application.help.command.ListHelpCommand;
import xinyongbang.application.help.representation.HelpRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by dw on 2016/5/23.
 */
public interface IHelpAppService {

    Pagination<HelpRepresentation> pagination(ListHelpCommand command);


    HelpRepresentation create(CreateHelpCommand command);

    HelpRepresentation searchByID(String id);

    HelpRepresentation edit(EditHelpCommand command);

    void updateStatus(SharedCommand command);
}
