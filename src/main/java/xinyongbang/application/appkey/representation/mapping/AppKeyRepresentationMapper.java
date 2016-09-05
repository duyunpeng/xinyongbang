package xinyongbang.application.appkey.representation.mapping;


import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import xinyongbang.application.appkey.representation.AppKeyRepresentation;
import xinyongbang.domain.model.appkey.AppKey;

/**
 * Created by YJH on 2016/3/30 0030.
 */
@Component
public class AppKeyRepresentationMapper extends CustomMapper<AppKey,AppKeyRepresentation> {
}
