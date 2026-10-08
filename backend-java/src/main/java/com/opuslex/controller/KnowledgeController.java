package com.opuslex.controller;
import com.opuslex.domain.governance.KnowledgePost;
import com.opuslex.domain.governance.KnowledgePostRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/v1/knowledge")
public class KnowledgeController {
    private final KnowledgePostRepository repo;
    public KnowledgeController(KnowledgePostRepository repo) { this.repo = repo; }
    @GetMapping
    public List<KnowledgePost> list(@org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { 
        return repo.findByUserId(principal.getId()); 
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KnowledgePost create(@RequestBody KnowledgePost post, @org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { 
        post.setUserId(principal.getId());
        return repo.save(post); 
    }
}
