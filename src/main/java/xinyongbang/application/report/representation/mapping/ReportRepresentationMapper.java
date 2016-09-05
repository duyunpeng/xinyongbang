package xinyongbang.application.report.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.account.representation.AccountRepresentation;
import xinyongbang.application.area.representation.AreaRepresentation;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.report.representation.ReportRepresentation;
import xinyongbang.application.user.representation.UserRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.area.Area;
import xinyongbang.domain.model.report.Report;

import java.util.List;

/**
 * Created by lvdi on 2016/4/14.
 */
@Component
public class ReportRepresentationMapper extends CustomMapper<Report, ReportRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Report report, ReportRepresentation representation, MappingContext context) {
        if (null != report.getReportUser()) {
            UserRepresentation data = mappingService.map(report.getReportUser(), UserRepresentation.class, false);
            representation.setReportUser(data);
        }
        if (null != report.getQuiltReportUser()) {
            UserRepresentation data = mappingService.map(report.getQuiltReportUser(), UserRepresentation.class, false);
            representation.setQuiltReportUser(data);
        }
        if (null != report.getPictures()) {
            List<PictureRepresentation> data = mappingService.mapAsList(report.getPictures(), PictureRepresentation.class);
            representation.setPictures(data);
        }
    }


}
