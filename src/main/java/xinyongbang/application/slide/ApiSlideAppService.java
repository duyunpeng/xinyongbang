package xinyongbang.application.slide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.slide.representation.SlideRepresentation;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.service.slide.ISlideService;

import java.util.List;

/**
 * Created by dyp on 2016/5/23.
 */
@Service("apiSlideAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiSlideAppService implements IApiSlideAppService {

    @Autowired
    private ISlideService slideService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse info() {
        List<SlideRepresentation> data = mappingService.mapAsList(slideService.allList(), SlideRepresentation.class);
        if (data == null || data.size()==0) {
            return new ApiResponse(ApiReturnCode.SUCCESS,ApiReturnCode.SUCCESS.getName(),null);
        }
        return new ApiResponse(ApiReturnCode.SUCCESS,ApiReturnCode.SUCCESS.getName(),data);
    }
}
