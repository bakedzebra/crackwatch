package com.vicefix.crackwatch;

import com.vicefix.crackwatch.hystrix.EnableHystrixModule;
import com.vicefix.crackwatch.service.GamesAPIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixModule
@EnableCaching
@EnableScheduling
public class InfoBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoBootApplication.class, args);
    }

}
