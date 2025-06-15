package com.example.SecurityJava;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/api/Output")
    public String Hello(){
        return "Hello!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/api/userEndpoint")
    public String userEndPoint(){
        return "Hello User!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/adminEndpoint")
    public String adminEndpoint(){
        return "Hello Admin!";
    }

}
