package com.bobfriend.bobfriends.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Operation(summary = "테스트 home")
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
