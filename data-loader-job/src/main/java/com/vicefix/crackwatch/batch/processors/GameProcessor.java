package com.vicefix.crackwatch.batch.processors;

import com.vicefix.crackwatch.elastic.model.Game;
import io.micrometer.core.lang.NonNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class GameProcessor implements ItemProcessor<List<Game>, List<Game>> {

    @Override
    public List<Game> process(@NonNull List<Game> games) {
        return games;
    }

}