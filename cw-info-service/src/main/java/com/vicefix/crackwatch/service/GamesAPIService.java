package com.vicefix.crackwatch.service;

import com.vicefix.crackwatch.model.GameDto;
import com.vicefix.crackwatch.model.enums.SortByOption;

import java.util.List;

public interface GamesAPIService {
    List<GameDto> getGames(Integer page, SortByOption sortBy, Boolean isSortInverted,
                           Boolean isAAA, Boolean isReleased, Boolean isCracked);
}