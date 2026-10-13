
package com.ensak.venteanalytics.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/ping")
    public String ping() {
        return "Backend connecté avec succès ✅";
    }
}
