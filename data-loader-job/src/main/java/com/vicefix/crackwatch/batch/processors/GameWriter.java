package com.vicefix.crackwatch.batch.processors;

import com.vicefix.crackwatch.batch.job.GamesFetchJob;
import com.vicefix.crackwatch.elastic.model.Game;
import com.vicefix.crackwatch.elastic.repository.GameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class GameWriter implements ItemWriter<List<Game>> {
    private static final Logger LOG = LogManager.getLogger(GamesFetchJob.class);

    private final GameRepository gameRepository;

    public GameWriter(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void write(@NonNull List<? extends List<Game>> result) {
        result.stream().findFirst().ifPresent(gameRepository::saveAll);
    }

}