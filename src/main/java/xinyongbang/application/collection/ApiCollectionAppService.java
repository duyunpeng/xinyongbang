package xinyongbang.application.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.collection.command.ListCollectionCommand;
import xinyongbang.application.collection.command.NewCollectionCommand;
import xinyongbang.application.collection.representation.ApiCollectionRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.collection.Collection;
import xinyongbang.domain.service.collection.ICollectionService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/5/24.
 */
@Service("apiCollectionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiCollectionAppService implements IApiCollectionAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private ICollectionService collectionService;

    @Override
    public ApiResponse newCollection(NewCollectionCommand command) {
        if (CoreStringUtils.isEmpty(command.getSource())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "source字段不能为空", null);
        }
        if (command.getSource().equals("chat")) {
            if (CoreStringUtils.isEmpty(command.getChat())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "chat字段不能为空", null);
            }
        } else if (command.getSource().equals("groupChat")) {
            if (CoreStringUtils.isEmpty(command.getGroupChat())) {
                return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "groupChat字段不能为空", null);
            }
        } else {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "未知source类型", null);
        }
        collectionService.apiCreate(command);
        return new ApiResponse(ApiReturnCode.SUCCESS);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse list(ListCollectionCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Collection> pagination = collectionService.apiPagination(command);
        List<ApiCollectionRepresentation> data = mappingService.mapAsList(pagination.getData(), ApiCollectionRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(),
                new Pagination<ApiCollectionRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize()));
    }
}
