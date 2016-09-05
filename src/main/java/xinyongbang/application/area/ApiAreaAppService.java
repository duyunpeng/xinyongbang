package xinyongbang.application.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.area.command.ListAreaCommand;
import xinyongbang.application.area.representation.ApiAreaRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.service.area.IAreaService;

import java.util.List;

/**
 * Created by YJH on 2016/4/17 0017.
 */
@Service("apiAreaAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiAreaAppService implements IApiAreaAppService {

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse searchByParent(ListAreaCommand command) {
        List<ApiAreaRepresentation> data = mappingService.mapAsList(areaService.list(command), ApiAreaRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse searchByID(SharedCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "id不能为空", null);
        }
        ApiAreaRepresentation data = mappingService.map(areaService.searchByID(command.getId()), ApiAreaRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

}
