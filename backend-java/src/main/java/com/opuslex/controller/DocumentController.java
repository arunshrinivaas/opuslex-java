package com.opuslex.controller;
import com.opuslex.dto.DocumentDto;
import com.opuslex.service.DocumentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
    private final DocumentService service;
    public DocumentController(DocumentService service) { this.service = service; }
    
    @GetMapping({"", "/"})
    public List<DocumentDto> listDocuments(@org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { return service.listDocuments(principal.getId()); }
    
    @GetMapping("/{id}")
    public DocumentDto getDocument(@PathVariable Integer id, @org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { return service.getDocument(id, principal.getId()); }

    @PostMapping({"", "/"})
    public org.springframework.http.ResponseEntity<?> uploadDocument(
            @RequestParam("file") org.springframework.web.multipart.MultipartFile file,
            @RequestParam(value = "title", required = false) String title,
            @org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) {
        DocumentDto dto = service.uploadDocument(file, title, principal.getId());
        return org.springframework.http.ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(dto);
    }
}
