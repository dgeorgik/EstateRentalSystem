package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 05.12.2024
 */

@Controller
@RequestMapping("/selected-service")
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
