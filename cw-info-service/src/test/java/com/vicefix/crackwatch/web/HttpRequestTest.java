package com.vicefix.crackwatch.web;

import com.netflix.ribbon.proxy.annotation.Http;
import com.vicefix.crackwatch.service.GamesAPIService;
import com.vicefix.crackwatch.web.impl.InfoControllerImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private GamesAPIService gamesAPIService;

	@InjectMocks
	private InfoControllerImpl infoController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(infoController);
	}

	@Test
	public void greetingShouldReturnDefaultMessage() {
		when(gamesAPIService.getGames(any(), any(), any(), any(), any(), any())).thenReturn(new ArrayList<>());

		List<HttpStatus> statuses = new ArrayList<>();

		for (ResponseEntity<String> response = null; response.getStatusCode() != HttpStatus.TOO_MANY_REQUESTS; response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/v1/info/games", String.class, Map.of())) {
			statuses.add(response.getStatusCode());
		}

		assertThat(statuses.get(0)).isEqualTo(HttpStatus.OK);
	}
}