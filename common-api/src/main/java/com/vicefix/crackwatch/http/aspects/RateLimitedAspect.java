package com.vicefix.crackwatch.http.aspects;

import com.vicefix.crackwatch.exceptions.BucketFullException;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimitedAspect {
    @Value("${http.rateLimiter.tokensAllowed}")
    private int tokensAllowed;

    @Value("${http.rateLimiter.period}")
    private long period;

    private final Bucket bucket;

    public RateLimitedAspect(Bucket bucket) {
        this.bucket = bucket;
    }

    @Before("@annotation(com.vicefix.crackwatch.http.annotations.RateLimited)")
    public void consumeBucketToken(JoinPoint joinPoint) {
        ConsumptionProbe probe = this.bucket.tryConsumeAndReturnRemaining(tokensAllowed);
        if (!probe.isConsumed()) {
            throw new BucketFullException("This API is time limited. Request token is not free yet.", probe.getNanosToWaitForRefill());
        }
    }

    @Before("@annotation(com.vicefix.crackwatch.http.annotations.RateLimitSleep)")
    public void waitForToken(JoinPoint joinPoint) throws InterruptedException {
        ConsumptionProbe probe = this.bucket.tryConsumeAndReturnRemaining(tokensAllowed);
        while (!probe.isConsumed()) {
            Thread.sleep(period);
            probe = this.bucket.tryConsumeAndReturnRemaining(tokensAllowed);
        }
    }

}
