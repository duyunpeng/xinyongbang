package xinyongbang.domain.service.golddetailed;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.golddetailed.command.CreateGoldDetailedCommand;
import xinyongbang.application.golddetailed.command.ListGoldDetailedCommand;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.model.golddetailed.GoldDetailed;
import xinyongbang.domain.model.golddetailed.IGoldDetailedRepository;
import xinyongbang.domain.service.user.IUserService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/4/19.
 */
@Service("goldDetailedService")
public class GoldDetailedService implements IGoldDetailedService {

    @Autowired
    private IGoldDetailedRepository<GoldDetailed, String> goldDetailedRepository;

    @Autowired
    private IUserService userService;

    @Override
    public Pagination<GoldDetailed> pagination(ListGoldDetailedCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("user.userName", command.getUserName(), MatchMode.ANYWHERE));
            aliasMap.put("user", "user");
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return goldDetailedRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public GoldDetailed create(CreateGoldDetailedCommand command) {
        User user = userService.searchByID(command.getUser());
        GoldDetailed goldDetailed = new GoldDetailed(user, command.getGoldNumber(), command.getFlowType(), command.getDescription());
        goldDetailedRepository.save(goldDetailed);
        return goldDetailed;
    }

    @Override
    public GoldDetailed searchByID(String id) {
        GoldDetailed goldDetailed = goldDetailedRepository.getById(id);
        if (null == goldDetailed) {
            throw new NoFoundException("没有找到ID[" + id + "]GoldDetailed");
        }
        return goldDetailed;
    }

}
