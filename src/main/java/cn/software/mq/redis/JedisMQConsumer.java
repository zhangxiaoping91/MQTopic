package cn.software.mq.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author ：zhangxpc@jiedaibao.com
 * @Description ： 基于redis的发布订阅模式的消息消费
 * @ClassName ：JedisMQConsumer  消息消费者
 * @date ：2017/4/7 16:01
 */
public class JedisMQConsumer  {
    private static final Logger log = LoggerFactory.getLogger(JedisMQConsumer.class);
    /**
     *  消费消息
     */
    public void receiveMsg(){
        log.info("==获取消息");
        Jedis jedis = null;
        jedis = JedisConnect.getInstance().pool.getResource();
        jedis.subscribe(new JedisPubSubHandler(),JedisConstant.PUBLISH_CHANNEL);
    }

    public static void main(String[] args) {
        new JedisMQConsumer().receiveMsg();
    }

}
