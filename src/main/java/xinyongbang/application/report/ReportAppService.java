package xinyongbang.application.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.application.report.command.CreateReportCommand;
import xinyongbang.application.report.command.EditReportCommand;
import xinyongbang.application.report.command.ListReportCommand;
import xinyongbang.application.report.representation.ReportRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.report.Report;
import xinyongbang.domain.service.report.IReportService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/19.
 */
@Service("reportAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ReportAppService implements IReportAppService{

    @Autowired
    private IReportService reportService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<ReportRepresentation> pagination(ListReportCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<Report> pagination=reportService.pagination(command);
        List<ReportRepresentation> data=mappingService.mapAsList(pagination.getData(),ReportRepresentation.class);

        return new Pagination<ReportRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public ReportRepresentation searchByID(String id) {
        return mappingService.map(reportService.searchByID(id),ReportRepresentation.class,false);
    }

    @Override
    public ReportRepresentation create(CreateReportCommand command) {
        return mappingService.map(reportService.apiCreate(command),ReportRepresentation.class,false);
    }

    @Override
    public void updateHandleStatus(SharedCommand command) {
        reportService.updateHandleStatus(command);
    }

    @Override
    public void finishReport(EditReportCommand command) {
        reportService.finishReport(command);
    }
}
