package cn.software.mq.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

/**
 * @author ：zhangxpc@jiedaibao.com
 * @Description ：发布消息处理
 * @ClassName ：JedisPubSubHandler
 * @date ：2017/4/7 16:11
 */
public class JedisPubSubHandler extends JedisPubSub {
    private static final Logger log = LoggerFactory.getLogger(JedisPubSubHandler.class);
    @Override
    public void onMessage(String channel, String message) {
        log.info("==receive msg-->{},channel-->{}",message,channel);
    }
}
