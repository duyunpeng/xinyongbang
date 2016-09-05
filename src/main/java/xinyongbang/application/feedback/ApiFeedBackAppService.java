package xinyongbang.application.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.feedback.command.CreateFeedBackCommand;
import xinyongbang.application.feedback.representation.FeedBackRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.service.feedback.IFeedBackService;


/**
 * Created by dyp on 2016/5/3.
 */
@Service("apiFeedBackAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiFeedBackAppService implements IApiFeedBackAppService {

    @Autowired
    private IFeedBackService feedBackService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public ApiResponse apiCreate(CreateFeedBackCommand command) {
        if (CoreStringUtils.isEmpty(command.getContent())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT,"content字段不能为空",null);
        }

        FeedBackRepresentation data = mappingService.map(feedBackService.apiCreate(command), FeedBackRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }
}
