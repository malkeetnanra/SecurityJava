package com.example.SecurityJava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/api/Output")
    public String Hello(){
        return "Hello!";
    }
}
