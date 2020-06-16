package com.vicefix.crackwatch.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface InfoController {
    ResponseEntity greetings();
}
