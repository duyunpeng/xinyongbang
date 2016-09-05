package xinyongbang.application.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.feedback.command.CreateFeedBackCommand;
import xinyongbang.application.feedback.command.EditFeedbackCommand;
import xinyongbang.application.feedback.command.ListFeedbackCommand;
import xinyongbang.application.feedback.representation.FeedBackRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.feedback.FeedBack;
import xinyongbang.domain.service.feedback.IFeedBackService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service("feedBackAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class FeedBackAppService implements IFeedBackAppService{
    @Autowired
    private IFeedBackService feedBackService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<FeedBackRepresentation> pagination(ListFeedbackCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<FeedBack> pagination = feedBackService.pagination(command);
        List<FeedBackRepresentation> data = mappingService.mapAsList(pagination.getData(),FeedBackRepresentation.class);
        return new Pagination<FeedBackRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public FeedBackRepresentation searchByID(String id) {
        return mappingService.map(feedBackService.searchByID(id),FeedBackRepresentation.class,false);
    }

    @Override
    public FeedBackRepresentation create(CreateFeedBackCommand command) {
        return mappingService.map(feedBackService.create(command),FeedBackRepresentation.class,false);
    }

    @Override
    public FeedBackRepresentation edit(EditFeedbackCommand command) {
        return mappingService.map(feedBackService.edit(command),FeedBackRepresentation.class,false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        feedBackService.updateStatus(command);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedBackRepresentation> listJSON(ListFeedbackCommand command) {
        return mappingService.mapAsList(feedBackService.list(command),FeedBackRepresentation.class);
    }
}
