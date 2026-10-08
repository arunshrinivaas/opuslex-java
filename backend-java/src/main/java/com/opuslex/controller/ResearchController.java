package com.opuslex.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/research/sessions")
public class ResearchController {
    // RAG and session execution is deferred to Phase 4
    @GetMapping
    public ResponseEntity<?> listSessions() { 
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("RAG execution deferred to later phase"); 
    }
    @PostMapping
    public ResponseEntity<?> createSession() { 
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("RAG execution deferred to later phase"); 
    }
}
