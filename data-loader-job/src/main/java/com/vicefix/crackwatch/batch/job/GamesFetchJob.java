package com.vicefix.crackwatch.batch.job;

import com.vicefix.crackwatch.batch.job.listeners.GamesFetchJobListener;
import com.vicefix.crackwatch.batch.processors.GameProcessor;
import com.vicefix.crackwatch.batch.processors.GameReader;
import com.vicefix.crackwatch.batch.processors.GameWriter;
import com.vicefix.crackwatch.elastic.model.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.vicefix.crackwatch.batch.registry.JobExecutionRegistry.*;

@Component
public class GamesFetchJob {
    private static final Logger LOG = LogManager.getLogger(GamesFetchJob.class);

    public static final String PAGE = "page";

    public static final String ALL_GAMES_FETCH_JOB_ID = "allGamesFetchJob";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobLauncher jobLauncher;

    private final GameProcessor gameProcessor;
    private final GameReader gameReader;
    private final GameWriter gameWriter;

    private final GamesFetchJobListener gamesFetchJobListener;

    public GamesFetchJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                         JobLauncher jobLauncher, GameProcessor gameProcessor, GameReader gameReader,
                         GameWriter gameWriter, GamesFetchJobListener gamesFetchJobListener) {

        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobLauncher = jobLauncher;
        this.gameProcessor = gameProcessor;
        this.gameReader = gameReader;
        this.gameWriter = gameWriter;
        this.gamesFetchJobListener = gamesFetchJobListener;
    }

    public void allGamesFetchJobStart(final boolean saveExecutionContext, final boolean restoreLastExecutionContext)
            throws Exception {

        Job job = jobBuilderFactory.get(ALL_GAMES_FETCH_JOB_ID)
                .incrementer(new RunIdIncrementer()).flow(gamesFetchStep())
                .end()
                .listener(gamesFetchJobListener)
                .build();

        JobParameters jobParameters = new JobParameters(Map.of(
                JOB_ID, new JobParameter(ALL_GAMES_FETCH_JOB_ID),
                SAVE_EXECUTION_CONTEXT, new JobParameter(String.valueOf(saveExecutionContext)),
                RESTORE_LAST_EXECUTION_CONTEXT, new JobParameter(String.valueOf(restoreLastExecutionContext))
        ));

        jobLauncher.run(job, jobParameters);
    }

    private Step gamesFetchStep() {
        return stepBuilderFactory.get("gamesFetchStep").<List<Game>, List<Game>>chunk(1)
                .reader(gameReader).processor(gameProcessor).writer(gameWriter)
                .build();
    }
}
