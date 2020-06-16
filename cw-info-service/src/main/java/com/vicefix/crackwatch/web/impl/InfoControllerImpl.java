package com.vicefix.crackwatch.web.impl;

import com.vicefix.crackwatch.service.InfoService;
import com.vicefix.crackwatch.web.InfoController;
import com.vicefix.crackwatch.web.InfoWebDefinitions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(InfoWebDefinitions.BASE_URL)
public class InfoControllerImpl implements InfoController {

    private final InfoService infoService;

    public InfoControllerImpl(InfoService infoService) {
        this.infoService = infoService;
    }

    @Override
    @GetMapping
    public ResponseEntity greetings() {
        return ResponseEntity.ok(infoService.greetings());
    }

}
