package xinyongbang.domain.service.feedback;

import xinyongbang.application.feedback.command.CreateFeedBackCommand;
import xinyongbang.application.feedback.command.EditFeedbackCommand;
import xinyongbang.application.feedback.command.ListFeedbackCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.feedback.FeedBack;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public interface IFeedBackService {
    Pagination<FeedBack> pagination(ListFeedbackCommand command);

    FeedBack searchByID(String id);

    FeedBack create(CreateFeedBackCommand command);

    FeedBack edit(EditFeedbackCommand command);

    void updateStatus(SharedCommand command);

    List<FeedBack> list(ListFeedbackCommand command);

    /***********Api 方法 **************/

    FeedBack apiCreate(CreateFeedBackCommand command);
}
