package com.opuslex.controller;

import com.opuslex.security.UserPrincipal;
import com.opuslex.dto.rag.RagRequest;
import com.opuslex.dto.rag.RagResponse;
import com.opuslex.service.RagService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/ask")
    public RagResponse askRag(
            @Valid @RequestBody RagRequest request,
            @AuthenticationPrincipal UserPrincipal currentUser
    ) {
        return ragService.askRag(request, currentUser.getId());
    }
}
