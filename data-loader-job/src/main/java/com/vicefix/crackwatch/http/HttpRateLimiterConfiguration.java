package com.vicefix.crackwatch.http;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class HttpRateLimiterConfiguration {

    @Value("${http.rateLimiter.capacity}")
    private int capacity;

    @Value("${http.rateLimiter.tokensAllowed}")
    private int tokensAllowed;

    @Value("${http.rateLimiter.period}")
    private long period;

    @Bean
    public Bucket httpBucket() {
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(capacity, Refill.greedy(tokensAllowed, Duration.ofMillis(period))))
                .build();
    }

    @Bean
    public RestTemplate httpClient() {
        return new RestTemplate();
    }

}
