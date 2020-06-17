package com.vicefix.crackwatch.web;

import com.vicefix.crackwatch.service.impl.GamesAPIServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InfoControllerITTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RestTemplate httpClient;

    @InjectMocks
    private GamesAPIServiceImpl gamesAPIService;

    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(gamesAPIService);
    }

    @Test
    public void greetingShouldReturnDefaultMessage() {
        when(httpClient.getForEntity(anyString(), any(), anyMap())).thenReturn(new ResponseEntity<>(List.of(), HttpStatus.OK));

        List<HttpStatus> statuses = Stream.of(getForGamesList(), getForGamesList())
                .map(ResponseEntity::getStatusCode)
                .sorted()
                .collect(Collectors.toList());

        statuses.stream().findFirst().ifPresent(status ->
                assertThat(status).isEqualTo(HttpStatus.OK)
        );

        statuses.stream().skip(1).findFirst().ifPresent(status ->
                assertThat(status).isEqualTo(HttpStatus.TOO_MANY_REQUESTS)
        );
    }

    private ResponseEntity<String> getForGamesList() {
        return this.restTemplate.getForEntity("http://localhost:" + port + "/api/v1/info/games", String.class, Map.of());
    }
}