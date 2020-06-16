package com.vicefix.crackwatch;

import com.vicefix.crackwatch.web.InfoController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BootTestPrepare {

	@Autowired
	private InfoController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}
}