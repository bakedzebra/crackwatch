package com.vicefix.crackwatch.batch.processors;

import com.vicefix.crackwatch.batch.job.GamesFetchJob;
import com.vicefix.crackwatch.elastic.model.Game;
import com.vicefix.crackwatch.http.annotations.ProgressJoinPoint;
import com.vicefix.crackwatch.http.annotations.RateLimitSleep;
import com.vicefix.crackwatch.service.GamesFetchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.vicefix.crackwatch.utils.CommonUtils.cast;

@Component
@Scope("prototype")
public class GameReader implements ItemReader<List<Game>>, StepExecutionListener {
    private static final Logger LOG = LogManager.getLogger(GamesFetchJob.class);

    private final GamesFetchService gamesFetchService;
    private StepExecution stepExecution;

    public GameReader(GamesFetchService gamesFetchService) {
        this.gamesFetchService = gamesFetchService;
    }

    @Override
    @RateLimitSleep
    @ProgressJoinPoint
    public List<Game> read() {
        int page = cast(this.stepExecution.getJobExecution().getExecutionContext().get(GamesFetchJob.PAGE));
        final List<Game> games = gamesFetchService.getGames(page);
        this.stepExecution.getJobExecution().getExecutionContext().put(GamesFetchJob.PAGE, ++page);
        return games.isEmpty() ? null : games;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return this.stepExecution.getExitStatus();
    }
}