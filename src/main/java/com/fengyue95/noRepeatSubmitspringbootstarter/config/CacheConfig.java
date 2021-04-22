package com.fengyue95.noRepeatSubmitspringbootstarter.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author fengyue
 * @date 2021/4/22
 */
@Configuration
@ConfigurationProperties(prefix = "cache")
@Data
public class CacheConfig {

    private String host;

    private String port;

    private String pwd;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        String redisUrl = String.format("redis://%s:%s", host, port);
        config.useSingleServer().setAddress(redisUrl).setPassword(pwd);
        return Redisson.create(config);
    }

}
