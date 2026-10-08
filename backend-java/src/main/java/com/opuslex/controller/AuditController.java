package com.opuslex.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
    @GetMapping("/findings")
    public ResponseEntity<?> findings() { return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Agent findings deferred"); }
    @GetMapping("/timeline")
    public ResponseEntity<?> timeline() { return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Agent timeline deferred"); }
    @GetMapping("/summary")
    public ResponseEntity<?> summary() { return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Agent summary deferred"); }
}
