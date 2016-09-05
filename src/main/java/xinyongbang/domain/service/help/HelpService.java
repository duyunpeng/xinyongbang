package xinyongbang.domain.service.help;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.help.command.CreateHelpCommand;
import xinyongbang.application.help.command.EditHelpCommand;
import xinyongbang.application.help.command.ListHelpCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.enums.EnableStatus;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.domain.model.help.Help;
import xinyongbang.domain.model.help.IHelpRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dw on 2016/5/23.
 */
@Service("helpService")
public class HelpService implements IHelpService {

    @Autowired
    private IHelpRepository<Help, String> helpRepository;

    @Override
    public Pagination<Help> pagination(ListHelpCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        if (null != command.getTitle()) {
            criterionList.add(Restrictions.like("title", command.getTitle(), MatchMode.ANYWHERE));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return helpRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Help create(CreateHelpCommand command) {

        if (null != helpRepository.getByTitle(command.getTitle())) {
            throw new ExistException("help[" + command.getTitle() + "]的help数据已存在");
        }
        Help help = new Help(command.getTitle(), command.getContent(), command.getStatus());
        helpRepository.save(help);
        return help;
    }

    @Override
    public Help searchByID(String id) {
        Help help = helpRepository.getById(id);
        if (null == help) {
            throw new NoFoundException("没有找到ID[" + id + "]的help");
        }
        return help;
    }

    @Override
    public Help edit(EditHelpCommand command) {
        Help help = this.searchByID(command.getId());
        if (null == help) {
            throw new NoFoundException("没有找到ID[" + command.getId() + "]的help");
        }
        help.changeTitle(command.getUpdateTitle());
        help.changeContent(command.getUpdateContent());
        help.changeStatus(command.getStatus());
        helpRepository.update(help);
        return help;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        Help help = this.searchByID(command.getId());
        help.fainWhenConcurrencyViolation(command.getVersion());
        if (help.getStatus() == EnableStatus.ENABLE) {
            help.changeStatus(EnableStatus.DISABLE);
        } else {
            help.changeStatus(EnableStatus.ENABLE);
        }
        helpRepository.update(help);
    }

    @Override
    public List<Help> apiAllList() {
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return helpRepository.list(null, orderList);
    }

}
