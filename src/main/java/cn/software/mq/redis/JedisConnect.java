package cn.software.mq.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ：zhangxpc@jiedaibao.com
 * @Description ：获取Jedis连接
 * @ClassName ：JedisConnect
 * @date ：2017/4/7 15:56
 */
public class JedisConnect {
    private static final Logger log = LoggerFactory.getLogger(JedisConnect.class);

    public  JedisPool pool;

    private static JedisConnect jedisConnect = null;

    public static JedisConnect getInstance() {
        if (jedisConnect == null) {
            synchronized (JedisConnect.class) {
                if (jedisConnect == null) {
                    jedisConnect = new JedisConnect();
                }
            }
        }
        return jedisConnect;
    }

    public JedisConnect() {
        log.info("==obtain redis connect");
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(5);
            config.setMaxWaitMillis(1000 * 100);
            config.setMaxTotal(20);
            pool = new JedisPool(config, JedisConstant.JEDIS_HOST, JedisConstant.PORT);
        }
    }
}
