package com.yany.common.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * spring bean形式引用
 */
public class JedisClusterFactory {

    private String redisConnectList;

    private Integer timeout;

    private Integer maxRedirections;

    private GenericObjectPoolConfig genericObjectPoolConfig;

    private redis.clients.jedis.JedisCluster jedisCluster = null;

    public redis.clients.jedis.JedisCluster getInstance() {
        return jedisCluster;
    }


    public void init() {
        String[] jedisClusterNodes = redisConnectList.split(",");
        Set<HostAndPort> jeAndPorts = new HashSet<>();
        for (String redisHost : jedisClusterNodes) {
            String[] hostAndPort = redisHost.split(":");
            jeAndPorts.add(new HostAndPort(hostAndPort[0], new Integer(hostAndPort[1])));
        }
        jedisCluster = new redis.clients.jedis.JedisCluster(jeAndPorts, timeout, maxRedirections,
                genericObjectPoolConfig);
    }

    public String getRedisConnectList() {
        return redisConnectList;
    }

    public void setRedisConnectList(String redisConnectList) {
        this.redisConnectList = redisConnectList;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxRedirections() {
        return maxRedirections;
    }

    public void setMaxRedirections(Integer maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public GenericObjectPoolConfig getGenericObjectPoolConfig() {
        return genericObjectPoolConfig;
    }

    public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
        this.genericObjectPoolConfig = genericObjectPoolConfig;
    }


}
