package com.vicefix.crackwatch.batch.registry;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JobExecutionRegistry {
    public static final String JOB_ID = "jobId";
    public static final String SAVE_EXECUTION_CONTEXT = "saveExecutionContext";
    public static final String RESTORE_LAST_EXECUTION_CONTEXT = "restoreLastExecutionContext";

    private final Map<String, JobRegistryEntry> internalRegistry;

    public JobExecutionRegistry() {
        this.internalRegistry = new HashMap<>();
    }

    public void save(@NonNull JobExecution jobExecution) {
        this.internalRegistry.put(jobExecution.getJobParameters().getString(JOB_ID), new JobRegistryEntry()
                .addExecutionContext(jobExecution.getExecutionContext())
                .addLastJobStatus(jobExecution.getStatus()));
    }

    public void restore(@NonNull JobExecution jobExecution) {
        JobRegistryEntry jobRegistryEntry = this.internalRegistry.getOrDefault(jobExecution.getJobParameters().getString(JOB_ID), new JobRegistryEntry());

        if (jobRegistryEntry.getLastJobStatus().isUnsuccessful()) {
            jobRegistryEntry.getSavedExecutionContext()
                    .forEach((key, value) -> jobExecution.getExecutionContext().put(key, value));
        }
    }

    public Optional<JobRegistryEntry> getJobRegistryEntry(String jobId) {
        return Optional.ofNullable(this.internalRegistry.get(jobId));
    }

    public static class JobRegistryEntry {
        private Map<String, Object> savedExecutionContext;
        private BatchStatus lastJobStatus;

        private JobRegistryEntry() {
            this.savedExecutionContext = new HashMap<>();
            this.lastJobStatus = BatchStatus.UNKNOWN;
        }

        public JobRegistryEntry addExecutionContext(ExecutionContext executionContext) {
            savedExecutionContext = executionContext.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            return this;
        }

        public JobRegistryEntry addLastJobStatus(BatchStatus batchStatus) {
            this.lastJobStatus = batchStatus;

            return this;
        }

        public BatchStatus getLastJobStatus() {
            return lastJobStatus;
        }

        public Map<String, Object> getSavedExecutionContext() {
            return savedExecutionContext;
        }
    }
}
