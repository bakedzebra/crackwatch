package com.vicefix.crackwatch.http.handlers;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class RateLimitInterceptor implements HandlerInterceptor {
    private final int consumableTokensPerRequest;
    private final Bucket bucket;

    public RateLimitInterceptor(Bucket bucket, int consumableTokensPerRequest) {
        this.bucket = bucket;
        this.consumableTokensPerRequest = consumableTokensPerRequest;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        ConsumptionProbe probe = this.bucket.tryConsumeAndReturnRemaining(consumableTokensPerRequest);
        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining",
                    Long.toString(probe.getRemainingTokens()));
            return true;
        }

        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.addHeader("X-Rate-Limit-Retry-After-Milliseconds",
                Long.toString(TimeUnit.NANOSECONDS.toMillis(probe.getNanosToWaitForRefill())));

        return false;

    }
}