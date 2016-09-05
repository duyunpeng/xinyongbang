package xinyongbang.application.report;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LvDi on 2016/4/21.
 */
@Service("apiReportAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiReportAppService implements IApiReportAppService{


}
