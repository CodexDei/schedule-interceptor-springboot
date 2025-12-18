package com.codexdei.springboot.calendar.interceptor.schedule.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request){

        Map<String,Object> data = new HashMap<>();
        
        data.put("title", "Welcome to the customer service system!");
        data.put("time", new Date().toString());
        data.put("message", request.getAttribute("message"));
        
        return ResponseEntity.ok(data);
    }

}
