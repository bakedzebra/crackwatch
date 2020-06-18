package com.vicefix.crackwatch.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Properties;

@Component
public class CacheOperationsService {
    private static final String CACHE_NAME_PATTERN = ".+[.].+[.name]";

    private final CacheManager cacheManager;
    private final Properties cacheConfigProperties;

    public CacheOperationsService(CacheManager cacheManager, Properties cacheConfigProperties) {
        this.cacheManager = cacheManager;
        this.cacheConfigProperties = cacheConfigProperties;
    }

    @Scheduled(fixedDelay = 300000)
    private void cacheEvict() {
        cacheConfigProperties.entrySet()
                .stream()
                .filter(entry -> String.valueOf(entry.getKey()).matches(CACHE_NAME_PATTERN))
                .map(entry -> String.valueOf(entry.getValue()))
                .forEach(name -> Optional.ofNullable(cacheManager.getCache(name)).ifPresent(Cache::clear));
    }
}
