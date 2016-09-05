package xinyongbang.domain.service.sign;

import xinyongbang.application.sign.command.SignCommand;
import xinyongbang.domain.model.user.User;

/**
 * Created by YJH on 2016/4/29.
 */
public interface ISignService {
    void apiSign(SignCommand command);

    boolean checkDate(User user);

    boolean apiJudgeSign(SignCommand command);
}
