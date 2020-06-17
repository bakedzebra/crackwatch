package com.vicefix.crackwatch.exceptions;

import java.util.concurrent.TimeUnit;

public class BucketFullException extends RuntimeException {
    private final long waitForRefillTime;

    public BucketFullException(String msg, long waitForRefillTime) {
        super(msg);

        this.waitForRefillTime = TimeUnit.NANOSECONDS.toMillis(waitForRefillTime);
    }

    public long getWaitForRefillTime() {
        return waitForRefillTime;
    }
}