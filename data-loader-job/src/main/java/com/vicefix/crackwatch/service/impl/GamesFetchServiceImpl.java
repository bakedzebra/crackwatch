package com.vicefix.crackwatch.service.impl;

import com.vicefix.crackwatch.elastic.model.Game;
import com.vicefix.crackwatch.http.HttpUtils;
import com.vicefix.crackwatch.service.GamesFetchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
public class GamesFetchServiceImpl implements GamesFetchService {
    private static final String GAMES_RESOURCE = "/games";

    private static final String PAGE_QUERY_PARAM = "page";

    @Value("${cw.baseUrl}")
    private String cwBaseUrl;

    private final RestTemplate httpClient;

    public GamesFetchServiceImpl(RestTemplate httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public List<Game> getGames(int page) {
        Map<String, Object> queryParameters = HttpUtils.newQueryParametersBuilder()
                .add(PAGE_QUERY_PARAM, page)
                .build();

        ResponseEntity<Game[]> response = httpClient.getForEntity(cwBaseUrl + GAMES_RESOURCE, Game[].class, queryParameters);

        return response.getStatusCode().is2xxSuccessful() && nonNull(response.getBody())
                ? Arrays.asList(response.getBody())
                : List.of();
    }
}