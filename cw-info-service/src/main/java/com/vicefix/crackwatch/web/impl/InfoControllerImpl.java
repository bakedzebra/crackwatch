package com.vicefix.crackwatch.web.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vicefix.crackwatch.hystrix.HystrixProperties;
import com.vicefix.crackwatch.model.GameDto;
import com.vicefix.crackwatch.model.enums.SortByOption;
import com.vicefix.crackwatch.service.GamesAPIService;
import com.vicefix.crackwatch.web.InfoController;
import com.vicefix.crackwatch.web.InfoWebDefinitions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(InfoWebDefinitions.BASE_URL)
public class InfoControllerImpl implements InfoController {

    private final GamesAPIService gamesAPIService;

    public InfoControllerImpl(GamesAPIService gamesAPIService) {
        this.gamesAPIService = gamesAPIService;
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = HystrixProperties.Execution.THREAD_TIMEOUT, value = "10000")
    })
    public ResponseEntity<List<GameDto>> getGames(Integer page, SortByOption sortBy, Boolean isSortInverted,
                                                  Boolean isAAA, Boolean isReleased, Boolean isCracked) {

        return ResponseEntity.ok(gamesAPIService.getGames(page, sortBy, isSortInverted, isAAA, isReleased, isCracked));
    }
}
