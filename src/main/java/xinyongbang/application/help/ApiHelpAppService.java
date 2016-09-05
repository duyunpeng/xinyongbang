package xinyongbang.application.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.help.command.ListHelpCommand;
import xinyongbang.application.help.representation.HelpRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.api.ApiResponse;
import xinyongbang.core.api.ApiReturnCode;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.core.util.CoreStringUtils;
import xinyongbang.domain.model.help.Help;
import xinyongbang.domain.service.help.IHelpService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by dw on 2016/5/23.
 */
@Service("apiHelpAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiHelpAppService implements  IApiHelpAppService{

    @Autowired
    private IMappingService mappingService ;

    @Autowired
    private IHelpService helpService ;


    @Override
    public ApiResponse list() {
        List<HelpRepresentation> data = mappingService.mapAsList(helpService.apiAllList(), HelpRepresentation.class);
        return new ApiResponse(ApiReturnCode.SUCCESS,ApiReturnCode.SUCCESS.getName(),data);
    }

    @Override
    public ApiResponse info(SharedCommand command) {
        if(CoreStringUtils.isEmpty(command.getId())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "帮助id(id)不能为空", null);
        }
        HelpRepresentation data = mappingService.map(helpService.searchByID(command.getId()), HelpRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

}
