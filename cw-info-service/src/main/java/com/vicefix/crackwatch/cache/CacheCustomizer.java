package com.vicefix.crackwatch.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
public class CacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {
    private static final String CACHE_NAME_PATTERN = ".+[.].+[.name]";

    private final Properties cacheConfigProperties;

    public CacheCustomizer(Properties cacheConfigProperties) {
        this.cacheConfigProperties = cacheConfigProperties;
    }

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        List<String> cacheNames = cacheConfigProperties.entrySet()
                .stream()
                .filter(entry -> String.valueOf(entry.getKey()).matches(CACHE_NAME_PATTERN))
                .map(entry -> String.valueOf(entry.getValue()))
                .collect(Collectors.toList());

        cacheManager.setCacheNames(cacheNames);
    }
}