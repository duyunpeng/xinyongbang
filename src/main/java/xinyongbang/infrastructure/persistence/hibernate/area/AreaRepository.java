package xinyongbang.infrastructure.persistence.hibernate.area;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.area.Area;
import xinyongbang.domain.model.area.IAreaRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/14.
 */
@Repository("areaRepository")
public class AreaRepository extends AbstractHibernateGenericRepository<Area, String>
        implements IAreaRepository<Area, String> {
}
