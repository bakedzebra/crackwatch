package com.vicefix.crackwatch.hystrix;

public final class HystrixProperties {
    public static class Execution {
        /**
         * This property indicates which isolation strategy HystrixCommand.run() executes with, one of the following two choices:
         *
         * THREAD — it executes on a separate thread and concurrent requests are limited by the number of threads in the thread-pool
         * SEMAPHORE — it executes on the calling thread and concurrent requests are limited by the semaphore count
         */
        public static final String STRATEGY = "execution.isolation.strategy";

        /**
         * This property sets the time in milliseconds after which the caller will observe a timeout and walk away from
         * the command execution. Hystrix marks the HystrixCommand as a TIMEOUT, and performs fallback logic.
         * Note that there is configuration for turning off timeouts per-command, if that is desired.
         */
        public static final String THREAD_TIMEOUT = "execution.isolation.thread.timeoutInMilliseconds";

        /**
         * This property indicates whether the HystrixCommand.run() execution should be interrupted when a timeout occurs.
         */
        public static final String THREAD_INTERRUPT_ON_TIMEOUT = "execution.isolation.thread.interruptOnTimeout";

        /**
         * This property indicates whether the HystrixCommand.run() execution should be interrupted when a cancellation occurs.
         */
        public static final String THREAD_INTERRUPT_ON_CANCEL = "execution.isolation.thread.interruptOnCancel";

        /**
         * This property indicates whether the HystrixCommand.run() execution should have a timeout.
         */
        public static final String TIMEOUT_ENABLED = "execution.timeout.enabled";

        public static final String STRATEGY_THREAD = "THREAD";
        public static final String STRATEGY_SEMAPHORE = "SEMAPHORE";
    }

    public static class Fallback {
        /**
         * This property sets the maximum number of requests a HystrixCommand.getFallback() method is allowed to make from the calling thread.
         *
         * If the maximum concurrent limit is hit then subsequent requests will be rejected and an exception thrown since no fallback could be retrieved.
         */
        public static final String MAX_CONCURRENT_REQUESTS = "fallback.isolation.semaphore.maxConcurrentRequests";

        /**
         * This property determines whether a call to HystrixCommand.getFallback() will be attempted when failure or rejection occurs.
         */
        public static final String FALLBACK_ENABLED = "fallback.enabled";
    }

    public static class CircuitBreaker {
        /**
         * This property determines whether a circuit breaker will be used to track health and to short-circuit requests if it trips.
         */
        public static final String CIRCUIT_BREAKER_ENABLED = "circuitBreaker.enabled";

        /**
         * This property sets the minimum number of requests in a rolling window that will trip the circuit.
         *
         * For example, if the value is 20, then if only 19 requests are received in the rolling window (say a window of 10 seconds) the circuit will not trip open even if all 19 failed.
         */
        public static final String REQUEST_VOLUME_THRESHOLD = "circuitBreaker.requestVolumeThreshold";

        /**
         * This property sets the amount of time, after tripping the circuit, to reject requests before allowing attempts again to determine if the circuit should again be closed.
         */
        public static final String SLEEP_WINDOW_IN_MILLISECONDS = "circuitBreaker.sleepWindowInMilliseconds";

        /**
         * This property sets the error percentage at or above which the circuit should trip open and start short-circuiting requests to fallback logic.
         */
        public static final String ERROR_THRESHOLD_PERCENTAGE = "circuitBreaker.errorThresholdPercentage";

        /**
         * This property, if true, forces the circuit breaker into an open (tripped) state in which it will reject all requests.
         *
         * This property takes precedence over circuitBreaker.forceClosed.
         */
        public static final String FORCE_OPEN = "circuitBreaker.forceOpen";

        /**
         * This property, if true, forces the circuit breaker into a closed state in which it will allow requests regardless of the error percentage.
         *
         * The circuitBreaker.forceOpen property takes precedence so if it is set to true this property does nothing.
         */
        public static final String FORCE_CLOSED = "circuitBreaker.forceClosed";
    }

    public static class Metrics {
        /**
         * This property sets the duration of the statistical rolling window, in milliseconds.
         * This is how long Hystrix keeps metrics for the circuit breaker to use and for publishing.
         */
        public static final String STATS_TIME_IN_MS = "metrics.rollingStats.timeInMilliseconds";

        /**
         * This property sets the number of buckets the rolling statistical window is divided into.
         *
         * Note: The following must be true — “metrics.rollingStats.timeInMilliseconds % metrics.rollingStats.numBuckets == 0” — otherwise it will throw an exception.
         */
        public static final String STATS_NUM_BUCKETS = "metrics.rollingStats.numBuckets";

        /**
         * This property indicates whether execution latencies should be tracked and calculated as percentiles. If they are disabled, all summary statistics (mean, percentiles) are returned as -1.
         */
        public static final String PERCENTILE_ENABLED = "metrics.rollingPercentile.enabled";

        /**
         * This property sets the duration of the rolling window in which execution times are kept to allow for percentile calculations, in milliseconds.
         */
        public static final String PERCENTILE_TIME_IN_MS = "metrics.rollingPercentile.timeInMilliseconds";

        /**
         * This property sets the number of buckets the rollingPercentile window will be divided into.
         *
         * Note: The following must be true — “metrics.rollingPercentile.timeInMilliseconds % metrics.rollingPercentile.numBuckets == 0” — otherwise it will throw an exception.
         */
        public static final String PERCENTILE_NUM_BUCKETS = "metrics.rollingPercentile.numBuckets";

        /**
         * This property sets the maximum number of execution times that are kept per bucket. If more executions occur during the time they will wrap around and start over-writing at the beginning of the bucket.
         *
         * For example, if bucket size is set to 100 and represents a bucket window of 10 seconds, but 500 executions occur during this time, only the last 100 executions will be kept in that 10 second bucket.
         *
         * If you increase this size, this also increases the amount of memory needed to store values and increases the time needed for sorting the lists to do percentile calculations.
         */
        public static final String PERCENTILE_BUCKET_SIZE = "metrics.rollingPercentile.bucketSize";

        /**
         * This property sets the time to wait, in milliseconds, between allowing health snapshots to be taken that calculate success and error percentages and affect circuit breaker status.
         *
         * On high-volume circuits the continual calculation of error percentages can become CPU intensive thus this property allows you to control how often it is calculated.
         */
        public static final String HEALTH_INTERVAL_IN_MS = "metrics.healthSnapshot.intervalInMilliseconds";
    }
}
