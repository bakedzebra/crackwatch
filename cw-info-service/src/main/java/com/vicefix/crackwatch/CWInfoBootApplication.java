package com.vicefix.crackwatch;

import com.vicefix.crackwatch.hystrix.EnableHystrixModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixModule
public class CWInfoBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CWInfoBootApplication.class, args);
    }

}
