package xinyongbang.infrastructure.persistence.hibernate.report;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.report.IReportRepository;
import xinyongbang.domain.model.report.Report;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("reportRepository")
public class ReportRepository extends AbstractHibernateGenericRepository<Report, String>
        implements IReportRepository<Report, String> {
}
