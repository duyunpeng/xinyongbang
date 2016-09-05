package xinyongbang.application.report.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xinyongbang.application.picture.representation.PictureRepresentation;
import xinyongbang.application.report.representation.ApiReportRepresentation;
import xinyongbang.core.mapping.IMappingService;
import xinyongbang.domain.model.report.Report;

import java.util.List;

/**
 * Created by YJH on 2016/5/18.
 */
@Component
public class ApiReportRepresentationMapper extends CustomMapper<Report, ApiReportRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Report report, ApiReportRepresentation representation, MappingContext context) {
        if (null != report.getReportUser()) {
            representation.setReportUser(report.getReportUser().getId());
            representation.setReportUserName(report.getReportUser().getUserName());
        }
        if (null != report.getQuiltReportUser()) {
            representation.setQuiltReportUser(report.getQuiltReportUser().getId());
            representation.setReportUserName(report.getQuiltReportUser().getUserName());
        }
        if (null != report.getPictures()) {
            List<PictureRepresentation> data = mappingService.mapAsList(report.getPictures(), PictureRepresentation.class);
            representation.setPictures(data);
        }
    }
}
