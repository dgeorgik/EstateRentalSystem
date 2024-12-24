package com.example.project.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 05.12.2024
 */

@RestController
@RequestMapping("/socket")
public class WebSocketController {


    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Main service connected to Notifier WebSocket!");
    }
}
