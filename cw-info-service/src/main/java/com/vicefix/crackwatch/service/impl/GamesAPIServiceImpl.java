package com.vicefix.crackwatch.service.impl;

import com.vicefix.crackwatch.http.HttpUtils;
import com.vicefix.crackwatch.http.annotations.RateLimited;
import com.vicefix.crackwatch.model.GameDto;
import com.vicefix.crackwatch.model.enums.SortByOption;
import com.vicefix.crackwatch.service.GamesAPIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static com.vicefix.crackwatch.utils.CommonUtils.cast;

@Service
public class GamesAPIServiceImpl implements GamesAPIService {
    private static final String GAMES_RESOURCE = "/games";

    private static final String PAGE_QUERY_PARAM = "page";
    private static final String SORT_BY_QUERY_PARAM = "sort_by";
    private static final String IS_SORT_INVERTED_QUERY_PARAM = "is_sort_inverted";
    private static final String IS_AAA_QUERY_PARAM = "is_aaa";
    private static final String IS_RELEASED_QUERY_PARAM = "is_released";
    private static final String IS_CRACKED_QUERY_PARAM = "is_cracked";

    @Value("${cw.baseUrl}")
    private String cwBaseUrl;

    private final RestTemplate httpClient;

    public GamesAPIServiceImpl(RestTemplate httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    @RateLimited
    @Cacheable("${cache.games.getGames.name}")
    public List<GameDto> getGames(Integer page, SortByOption sortBy, Boolean isSortInverted,
                                  Boolean isAAA, Boolean isReleased, Boolean isCracked) {

        Map<String, Object> queryParameters = HttpUtils.newQueryParametersBuilder()
                .add(PAGE_QUERY_PARAM, page)
                .add(SORT_BY_QUERY_PARAM, sortBy)
                .add(IS_SORT_INVERTED_QUERY_PARAM, isSortInverted)
                .add(IS_AAA_QUERY_PARAM, isAAA)
                .add(IS_RELEASED_QUERY_PARAM, isReleased)
                .add(IS_CRACKED_QUERY_PARAM, isCracked)
                .build();

        var response = httpClient.getForEntity(cwBaseUrl + GAMES_RESOURCE, List.class, queryParameters);
        return response.getStatusCode().is2xxSuccessful()
                ? cast(response.getBody())
                : List.of();
    }
}