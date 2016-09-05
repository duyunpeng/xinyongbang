package xinyongbang.infrastructure.persistence.hibernate.album;

import org.springframework.stereotype.Repository;
import xinyongbang.domain.model.album.Album;
import xinyongbang.domain.model.album.IAlbumRepository;
import xinyongbang.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/5/13.
 */
@Repository("albumRepository")
public class AlbumRepository extends AbstractHibernateGenericRepository<Album, String>
        implements IAlbumRepository<Album, String> {
}
