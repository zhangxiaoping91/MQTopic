package cn.software.mq.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author ：zhangxpc@jiedaibao.com
 * @Description ：基于redis来实现消息队列
 * @ClassName ：JedisMQProvider消息生产者
 * @date ：2017/4/7 14:45
 */
public class JedisMQProvider {
    private final static Logger log = LoggerFactory.getLogger(JedisMQProvider.class);

    private static JedisMQProvider jedisMQProvider = null;
    private JedisMQProvider() {
    }
    public static JedisMQProvider getInstance() {
        if (jedisMQProvider == null) {
            synchronized (JedisMQProvider.class) {
                if (jedisMQProvider == null) {
                    jedisMQProvider = new JedisMQProvider();
                }
            }
        }
        return jedisMQProvider;
    }

    /**
     * 发布消息
     *
     * @param msgJson 消息体
     */
    public void sendMsg(String msgJson) {
        Jedis jedis = null;
        try {
            jedis = JedisConnect.getInstance().pool.getResource();
            log.info("==send msg-->{}", msgJson);
            jedis.publish(JedisConstant.PUBLISH_CHANNEL, msgJson);
            log.info("==send msg success");
        } catch (Exception e) {
            log.error("==msg publish Exception!!!", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void main(String[] args) {
        JedisMQProvider.getInstance().sendMsg("my name is zhangxiaoping");
    }

}
