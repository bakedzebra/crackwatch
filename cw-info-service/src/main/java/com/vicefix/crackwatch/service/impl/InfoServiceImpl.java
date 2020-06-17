package com.vicefix.crackwatch.service.impl;

import com.vicefix.crackwatch.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

@Service
public class InfoServiceImpl implements InfoService {

    @Value("${cw.baseUrl}")
    private String cwBaseUrl;

    private final HttpClient httpClient;

    public InfoServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
