package xinyongbang.domain.service.clicklike;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.clicklike.command.ClickLikeCommand;
import xinyongbang.application.golddetailed.command.CreateGoldDetailedCommand;
import xinyongbang.core.enums.FlowType;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.MoneyNotEnoughException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.clicklike.ClickLike;
import xinyongbang.domain.model.clicklike.IClickLikeRepository;
import xinyongbang.domain.model.user.User;
import xinyongbang.domain.service.golddetailed.IGoldDetailedService;
import xinyongbang.domain.service.user.IUserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/4/29.
 */
@Service("clickLickService")
public class ClickLickService implements IClickLickService {

    @Autowired
    private IClickLikeRepository<ClickLike, String> clickLikeRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoldDetailedService goldDetailedService;

    @Override
    public ClickLike searchByUser(String user, String clickUser) {
        return clickLikeRepository.searchByUser(user, clickUser);
    }

    @Override
    public void apiClickLike(ClickLikeCommand command) {
        User user = userService.searchByID(command.getUser());
        User clickUser = userService.searchByID(command.getClickUser());
        if (clickUser.getGold().compareTo(new BigDecimal(1)) == -1) {
            throw new MoneyNotEnoughException("用户[" + clickUser.getUserName() + "]余额不足");
        }
        if (null != this.searchByUser(user.getId(), clickUser.getId())) {
            throw new ExistException("用户[" + clickUser.getUserName() + "]已为[" + user.getUserName() + "]点赞");
        }

        ClickLike clickLike = new ClickLike(user, clickUser);
        clickLikeRepository.save(clickLike);

        CreateGoldDetailedCommand goldDetailedCommand = new CreateGoldDetailedCommand();
        goldDetailedCommand.setUser(clickUser.getId());
        goldDetailedCommand.setDescription("点赞");
        goldDetailedCommand.setFlowType(FlowType.OUT_FLOW);
        goldDetailedCommand.setGoldNumber(new BigDecimal(1));
        goldDetailedService.create(goldDetailedCommand);

        clickUser.changeGold(clickUser.getGold().subtract(new BigDecimal(1)));

        userService.update(clickUser);

        user.changeCredit(user.getCredit() + 1);
        userService.update(user);
    }

    @Override
    public void apiCancelClickLike(ClickLikeCommand command) {
        ClickLike clickLike = this.searchByUser(command.getUser(), command.getClickUser());
        if (null == clickLike) {
            throw new NoFoundException("没有找到点赞记录");
        }
        clickLikeRepository.delete(clickLike);

        User user = clickLike.getUser();
        user.changeCredit(user.getCredit() - 1);
        userService.update(user);
    }

    @Override
    public List<ClickLike> list(ClickLikeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getUser())) {
            criterionList.add(Restrictions.eq("user.id", command.getUser()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return clickLikeRepository.list(criterionList, orderList);
    }
}
