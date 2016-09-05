package xinyongbang.infrastructure.persistence.hibernate.slide;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.slide.ISlideRepository;
import xinyongbang.domain.model.slide.Slide;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/15.
 */
@Repository("slideRepository")
public class SlideRepository extends AbstractHibernateGenericRepository<Slide, String>
        implements ISlideRepository<Slide, String> {
}
