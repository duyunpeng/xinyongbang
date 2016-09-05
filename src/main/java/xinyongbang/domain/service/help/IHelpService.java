package xinyongbang.domain.service.help;

import xinyongbang.application.help.command.CreateHelpCommand;
import xinyongbang.application.help.command.EditHelpCommand;
import xinyongbang.application.help.command.ListHelpCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.help.Help;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dw on 2016/5/23.
 */
public interface IHelpService {

    Pagination<Help> pagination(ListHelpCommand command);

    Help create(CreateHelpCommand command);

    Help edit(EditHelpCommand command);

    void updateStatus(SharedCommand command);

    Help searchByID(String id);


    /**********   api方法    **********/
    List<Help> apiAllList();

}
