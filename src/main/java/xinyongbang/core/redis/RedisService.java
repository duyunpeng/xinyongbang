package xinyongbang.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.infrastructure.persistence.redis.IRedisRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by YJH on 2016/3/21.
 */
@Service("redisService")
public class RedisService {

    static private final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    @Autowired
    private IRedisRepository<String, String> redisRepository;

    @Autowired
    private RedisTime time;

    public boolean exists(final String key) {
        return redisRepository.exists(key);
    }

    public void addCache(final String key, final String value) {
        redisRepository.addCache(key, value, time.getTime(), DEFAULT_TIME_UNIT);
    }

    public void addCaches(final String key, final List value) {
        redisRepository.addCache(key, value.toString(), time.getTime(), DEFAULT_TIME_UNIT);
    }

    public String getCache(final String key) {
        return redisRepository.getCache(key).toString();
    }

    public List getCaches(final String key) {
        String a = redisRepository.getCache(key);
        List b = new ArrayList();
        b.add(a);
        return b;
    }

    public void delete(final String key) {
        redisRepository.remove(key);
    }

}
