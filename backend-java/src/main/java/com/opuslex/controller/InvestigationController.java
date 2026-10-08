package com.opuslex.controller;
import com.opuslex.dto.InvestigationDto;
import com.opuslex.dto.InvestigationRequestDto;
import com.opuslex.service.InvestigationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/investigations")
public class InvestigationController {
    private final InvestigationService service;
    public InvestigationController(InvestigationService service) { this.service = service; }
    
    @GetMapping({"", "/"})
    public List<InvestigationDto> listInvestigations(@org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { return service.listInvestigations(principal.getId()); }
    
    @GetMapping("/{id}")
    public InvestigationDto getInvestigation(@PathVariable Integer id, @org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { return service.getInvestigation(id, principal.getId()); }
    
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public InvestigationDto createInvestigation(@RequestBody InvestigationRequestDto req, @org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { return service.createInvestigation(req, principal.getId()); }
    
    @PatchMapping("/{id}")
    public InvestigationDto updateInvestigation(@PathVariable Integer id, @RequestBody InvestigationRequestDto req, @org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { return service.updateInvestigation(id, req, principal.getId()); }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvestigation(@PathVariable Integer id, @org.springframework.security.core.annotation.AuthenticationPrincipal com.opuslex.security.UserPrincipal principal) { service.deleteInvestigation(id, principal.getId()); }
}
