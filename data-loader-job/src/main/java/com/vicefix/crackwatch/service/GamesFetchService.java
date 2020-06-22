package com.vicefix.crackwatch.service;

import com.vicefix.crackwatch.elastic.model.Game;

import java.util.List;

public interface GamesFetchService {
    List<Game> getGames(int page);
}