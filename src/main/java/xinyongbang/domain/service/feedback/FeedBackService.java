package xinyongbang.domain.service.feedback;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.feedback.command.CreateFeedBackCommand;
import xinyongbang.application.feedback.command.EditFeedbackCommand;
import xinyongbang.application.feedback.command.ListFeedbackCommand;
import xinyongbang.application.picture.command.CreatePictureCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.enums.HandleStatus;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.feedback.FeedBack;
import xinyongbang.domain.model.feedback.IFeedBackRepository;
import xinyongbang.domain.model.picture.IPictureRepository;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.service.picture.IPictureService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyp on 2016/4/19.
 */
@Service("feedBackService")
public class FeedBackService implements IFeedBackService {

    @Autowired
    private IFeedBackRepository<FeedBack, String> feedBackRepository;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired
    private IPictureService pictureService;

    @Override
    public Pagination<FeedBack> pagination(ListFeedbackCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getEmail())) {
            criterionList.add(Restrictions.like("email", command.getEmail(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getPhone())) {
            criterionList.add(Restrictions.like("phone", command.getPhone(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getQq())) {
            criterionList.add(Restrictions.like("qq", command.getQq(), MatchMode.ANYWHERE));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return feedBackRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public FeedBack searchByID(String id) {
        FeedBack feedBack = feedBackRepository.getById(id);
        if (null == feedBack) {
            throw new NoFoundException("没有找到ID[" + id + "]的FeedBack数据");
        }
        return feedBack;
    }

    @Override
    public FeedBack create(CreateFeedBackCommand command) {
        FeedBack feedBack = new FeedBack(command.getContent(), command.getPhone(), command.getStatus(), null);
        feedBackRepository.save(feedBack);
        return feedBack;
    }

    @Override
    public FeedBack apiCreate(CreateFeedBackCommand command) {
        List<Picture> pictureList = null;
        if (null != command.getPictureList()) {
            if (command.getPictureList().size() > 0) {
                pictureList = new ArrayList<Picture>();
                for (String picture : command.getPictureList()) {
                    CreatePictureCommand createPictureCommand = fileUploadService.moveToImg(picture);
                    Picture picture1 = pictureService.create(createPictureCommand);
                    pictureList.add(picture1);
                }
            }
        }
        FeedBack feedBack = new FeedBack(command.getContent(), command.getPhone(), HandleStatus.WAIT_HANDLE, pictureList);
        feedBackRepository.save(feedBack);
        return feedBack;
    }

    @Override
    public FeedBack edit(EditFeedbackCommand command) {
        FeedBack feedBack = this.searchByID(command.getId());
        feedBack.fainWhenConcurrencyViolation(command.getVersion());
        feedBack.changePhone(command.getPhone());
        feedBack.changeContent(command.getContent());
        feedBack.changeStatus(command.getStatus());
        feedBackRepository.update(feedBack);
        return feedBack;
    }

    @Override
    public void updateStatus(SharedCommand command) {
        FeedBack feedBack = this.searchByID(command.getId());

        feedBack.fainWhenConcurrencyViolation(command.getVersion());
        if (feedBack.getStatus() == HandleStatus.WAIT_HANDLE) {
            feedBack.changeStatus(HandleStatus.IN_HANDLE);
        } else if (feedBack.getStatus() == HandleStatus.IN_HANDLE) {
            feedBack.changeStatus(HandleStatus.OK_HANDLE);
        }
        feedBackRepository.update(feedBack);
    }

    @Override
    public List<FeedBack> list(ListFeedbackCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createData"));
        return feedBackRepository.list(criterionList, orderList);
    }


}
