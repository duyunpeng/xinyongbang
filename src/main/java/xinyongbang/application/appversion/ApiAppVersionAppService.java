package xinyongbang.application.appversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.appkey.representation.AppKeyRepresentation;
import xinyongbang.application.appversion.representation.AppVersionRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.service.appversion.IAppVersionService;

import java.util.List;


/**
 * Created by dyp on 2016/5/3.
 */
@Service("apiAppVersionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiAppVersionAppService implements IApiAppVersionAppService {

    @Autowired
    private IAppVersionService appVersionService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse info() {
        List<AppVersionRepresentation> data = mappingService.mapAsList(appVersionService.allList(), AppVersionRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data.size() < 1 ? null : data.get(0));
    }
}
