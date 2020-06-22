package com.vicefix.crackwatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
public class DataLoaderJobStarter {

    public static void main(String[] args) {
        SpringApplication springApplication =
                new SpringApplicationBuilder()
                        .sources(DataLoaderJobStarter.class)
                        .web(WebApplicationType.NONE)
                        .build();

        springApplication.run(args);
    }

}
