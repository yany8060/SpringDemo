package com.yany.common.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * jar包形式,引入属性文件
 */
public class JedisClusertApi {

    private static JedisClusterFactory jedisClusterFactory = null;

    private static void getFactory() {
        if (jedisClusterFactory == null) {
            InputStream input =
                    JedisClusertApi.class.getResourceAsStream("/properties/connect-redis.properties");
            Properties properties = new Properties();
            try {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            genericObjectPoolConfig
                    .setMaxWaitMillis(Long.valueOf((String) properties.get("redis.generic.maxWaitMillis")));
            genericObjectPoolConfig
                    .setMaxTotal(Integer.valueOf((String) properties.get("redis.generic.maxTotal")));
            genericObjectPoolConfig
                    .setMinIdle(Integer.valueOf((String) properties.get("redis.generic.minIdle")));
            genericObjectPoolConfig
                    .setMaxIdle(Integer.valueOf((String) properties.get("redis.generic.maxIdle")));

            jedisClusterFactory = new JedisClusterFactory();
            jedisClusterFactory.setRedisConnectList((String) properties.get("redis.connect.list"));
            jedisClusterFactory
                    .setTimeout(Integer.valueOf((String) properties.get("redis.connect.timeout")));
            jedisClusterFactory.setMaxRedirections(
                    Integer.valueOf((String) properties.get("redis.connect.maxRedirections")));
            jedisClusterFactory.setGenericObjectPoolConfig(genericObjectPoolConfig);


            jedisClusterFactory.init();
        }
    }

    public static JedisCluster getRedisCluster() {
        if (jedisClusterFactory == null || jedisClusterFactory.getInstance() == null) {
            getFactory();
        }
        return jedisClusterFactory.getInstance();
    }


}
