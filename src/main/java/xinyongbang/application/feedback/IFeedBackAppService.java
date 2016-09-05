package xinyongbang.application.feedback;


import xinyongbang.application.feedback.command.CreateFeedBackCommand;
import xinyongbang.application.feedback.command.EditFeedbackCommand;
import xinyongbang.application.feedback.command.ListFeedbackCommand;
import xinyongbang.application.feedback.representation.FeedBackRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public interface IFeedBackAppService {
    Pagination<FeedBackRepresentation> pagination(ListFeedbackCommand command);

    FeedBackRepresentation searchByID(String id);

    FeedBackRepresentation create(CreateFeedBackCommand command);

    FeedBackRepresentation edit(EditFeedbackCommand command);

    void updateStatus(SharedCommand command);

    List<FeedBackRepresentation> listJSON(ListFeedbackCommand command);
}
