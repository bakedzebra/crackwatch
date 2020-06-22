package com.vicefix.crackwatch.batch.job.listeners;

import com.vicefix.crackwatch.batch.job.GamesFetchJob;
import com.vicefix.crackwatch.batch.registry.JobExecutionRegistry;
import com.vicefix.crackwatch.http.annotations.ProgressEnd;
import com.vicefix.crackwatch.http.annotations.ProgressStart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Component;

import static com.vicefix.crackwatch.batch.job.GamesFetchJob.PAGE;
import static com.vicefix.crackwatch.batch.registry.JobExecutionRegistry.*;

@Component
public class GamesFetchJobListener implements JobExecutionListener {
    private static final Logger LOG = LogManager.getLogger(GamesFetchJob.class);

    private final JobExecutionRegistry jobExecutionRegistry;

    public GamesFetchJobListener(JobExecutionRegistry jobExecutionRegistry) {
        this.jobExecutionRegistry = jobExecutionRegistry;
    }

    @Override
    @ProgressStart
    public void beforeJob(JobExecution jobExecution) {
        JobParameters jobParameters = jobExecution.getJobParameters();
        if (Boolean.parseBoolean(jobParameters.getString(RESTORE_LAST_EXECUTION_CONTEXT))) {
            jobExecutionRegistry.getJobRegistryEntry(jobParameters.getString(JOB_ID))
                    .ifPresent(jobRegistryEntry -> {
                        if (jobRegistryEntry.getLastJobStatus().isUnsuccessful()) {
                            jobExecutionRegistry.restore(jobExecution);
                            LOG.info("Last job state was restored successfully");
                        }
                    });
        } else {
            jobExecution.getExecutionContext()
                    .put(PAGE, 0);
        }
    }

    @Override
    @ProgressEnd
    public void afterJob(JobExecution jobExecution) {
        JobParameters jobParameters = jobExecution.getJobParameters();
        if (jobExecution.getStatus().isUnsuccessful()
                && Boolean.parseBoolean(jobParameters.getString(SAVE_EXECUTION_CONTEXT))) {

            this.jobExecutionRegistry.save(jobExecution);
            LOG.info(String.format("Execution context for job %s was saved. Please use the --restore-last-exec-context flag for the next run to restore the context.", jobExecution.getId()));
        }

        LOG.info(String.format("The job finished with status %s.", jobExecution.getStatus().name()));
    }
}
