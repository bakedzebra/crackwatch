package com.vicefix.crackwatch.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class CacheProperties {

    @Bean
    @ConfigurationProperties(prefix = "cache")
    public Properties cacheConfigProperties() {
        return new Properties();
    }

}
