package com.vicefix.crackwatch.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() {
		long firstValidRequestTimestamp = System.currentTimeMillis();
		ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/v1/info", String.class, Map.of());
		if (response.getStatusCode() == HttpStatus.OK) {
			do {
				response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/v1/info", String.class, Map.of());
				if (response.getStatusCode() != HttpStatus.OK) {
					assertThat(response.getStatusCode()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);
				}
			} while (response.getStatusCode() != HttpStatus.OK);

			long lastValidRequestTimestamp = System.currentTimeMillis();

			assertThat(lastValidRequestTimestamp - firstValidRequestTimestamp).isGreaterThanOrEqualTo(1200);
		}
	}
}