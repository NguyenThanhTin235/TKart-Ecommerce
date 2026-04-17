package com.tkart.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleApiController {

     @GetMapping("/hello")
     public String hello() {
          return "Hello from Swagger sample API!";
     }
}