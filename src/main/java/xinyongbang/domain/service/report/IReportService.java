package xinyongbang.domain.service.report;

import xinyongbang.application.report.command.CreateReportCommand;
import xinyongbang.application.report.command.EditReportCommand;
import xinyongbang.application.report.command.ListReportCommand;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.domain.model.report.Report;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by Administrator on 2016/4/19.
 */
public interface IReportService {
    Pagination<Report> pagination(ListReportCommand command);

    Report searchByID(String id);

    void updateHandleStatus(SharedCommand command);

    void finishReport(EditReportCommand command);

    /***********Api 方法 **************/

    Report apiCreate(CreateReportCommand command);

}
