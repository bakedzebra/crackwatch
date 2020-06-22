package com.vicefix.crackwatch.batch.managing;

import com.vicefix.crackwatch.batch.job.GamesFetchJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent("games")
public class GamesAPICommands {
    private static final Logger LOG = LoggerFactory.getLogger(GamesAPICommands.class);

    private final GamesFetchJob gamesFetchJob;

    public GamesAPICommands(GamesFetchJob gamesFetchJob) {
        this.gamesFetchJob = gamesFetchJob;
    }

    @ShellMethod(key = {"games load"}, value = "Starts a load job for a Games endpoint")
    public void loadGames(@ShellOption("--save-exec-context") boolean saveExecutionContext,
                          @ShellOption("--restore-last-exec-context") boolean restoreLastExecutionContext) {

        try {
            this.gamesFetchJob.allGamesFetchJobStart(saveExecutionContext, restoreLastExecutionContext);
        } catch (Exception e) {
            LOG.error("Error occurred during games fetch job execution", e);
        }
    }
}