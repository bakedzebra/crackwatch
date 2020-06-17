package com.vicefix.crackwatch.web;

import com.vicefix.crackwatch.model.GameDto;
import com.vicefix.crackwatch.model.enums.SortByOption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.vicefix.crackwatch.web.InfoWebDefinitions.*;

public interface InfoController {

    @GetMapping(GAMES_RESOURCE)
    ResponseEntity<List<GameDto>> getGames(@RequestParam(value = PAGE_QUERY_PARAM, required = false) Integer page,
                                           @RequestParam(value = SORT_BY_QUERY_PARAM, required = false) SortByOption sortBy,
                                           @RequestParam(value = IS_SORT_INVERTED_QUERY_PARAM, required = false) Boolean isSortInverted,
                                           @RequestParam(value = IS_AAA_QUERY_PARAM, required = false) Boolean isAAA,
                                           @RequestParam(value = IS_RELEASED_QUERY_PARAM, required = false) Boolean isReleased,
                                           @RequestParam(value = IS_CRACKED_QUERY_PARAM, required = false) Boolean isCracked);
}
