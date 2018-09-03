package com.melody.redis;


import com.melody.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisPoolConfig {

    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.jedis.pool.max-active}") int maxActive,
                                           @Value("${spring.redis.jedis.pool.max-idle}") int maxIdle,
                                           @Value("${spring.redis.jedis.pool.max-wait}") int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }

    @Bean(name = "shardedJedisPool")
    @Scope("singleton")
    public ShardedJedisPool shardedJedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig,
                                             @Value("${spring.redis.host}") String host,
                                             @Value("${spring.redis.port}") int port,
                                             @Value("${spring.redis.password}") String password) {
        List<JedisShardInfo> shards = new ArrayList<>();


        JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port);
        if (!StringUtils.isEmpty(password)) {
            jedisShardInfo.setPassword(password);
        }
        shards.add(jedisShardInfo);
        return new ShardedJedisPool(jedisPoolConfig, shards);
    }


}