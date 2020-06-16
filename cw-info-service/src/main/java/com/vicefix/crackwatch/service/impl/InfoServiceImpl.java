package com.vicefix.crackwatch.service.impl;

import com.vicefix.crackwatch.service.InfoService;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Override
    public String greetings() {
        return "Foobar!";
    }

}
