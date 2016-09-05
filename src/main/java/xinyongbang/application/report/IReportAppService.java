package xinyongbang.application.report;

import xinyongbang.application.report.command.CreateReportCommand;
import xinyongbang.application.report.command.EditReportCommand;
import xinyongbang.application.report.command.ListReportCommand;
import xinyongbang.application.report.representation.ReportRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by LvDi on 2016/4/19.
 */
public interface IReportAppService {
    Pagination<ReportRepresentation> pagination(ListReportCommand command);

    ReportRepresentation searchByID(String id);

    ReportRepresentation create(CreateReportCommand command);

    void updateHandleStatus(SharedCommand command);

    void finishReport(EditReportCommand command);
}
