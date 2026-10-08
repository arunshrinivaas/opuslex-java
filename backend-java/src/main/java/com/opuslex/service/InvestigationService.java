package com.opuslex.service;
import com.opuslex.domain.investigation.Investigation;
import com.opuslex.domain.investigation.InvestigationRepository;
import com.opuslex.dto.InvestigationDto;
import com.opuslex.dto.InvestigationRequestDto;
import com.opuslex.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
@Transactional
public class InvestigationService {
    private final InvestigationRepository repository;
    public InvestigationService(InvestigationRepository repository) { this.repository = repository; }
    
    @Transactional(readOnly = true)
    public List<InvestigationDto> listInvestigations(Integer userId) {
        return repository.findByUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public InvestigationDto getInvestigation(Integer id, Integer userId) {
        return repository.findByIdAndUserId(id, userId).map(this::mapToDto)
            .orElseThrow(() -> new ResourceNotFoundException("Investigation not found"));
    }
    
    public InvestigationDto createInvestigation(InvestigationRequestDto req, Integer userId) {
        Investigation inv = new Investigation();
        inv.setUserId(userId);
        inv.setTitle(req.getTitle());
        inv.setDescription(req.getDescription());
        inv.setStatus(req.getStatus() != null ? req.getStatus() : "OPEN");
        inv.setCreatedAt(LocalDateTime.now());
        inv.setUpdatedAt(LocalDateTime.now());
        return mapToDto(repository.save(inv));
    }
    
    public InvestigationDto updateInvestigation(Integer id, InvestigationRequestDto req, Integer userId) {
        Investigation inv = repository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Investigation not found"));
        if(req.getTitle() != null) inv.setTitle(req.getTitle());
        if(req.getDescription() != null) inv.setDescription(req.getDescription());
        if(req.getStatus() != null) inv.setStatus(req.getStatus());
        inv.setUpdatedAt(LocalDateTime.now());
        return mapToDto(repository.save(inv));
    }
    
    public void deleteInvestigation(Integer id, Integer userId) {
        Investigation inv = repository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Investigation not found"));
        repository.delete(inv);
    }
    
    private InvestigationDto mapToDto(Investigation inv) {
        InvestigationDto dto = new InvestigationDto();
        dto.setId(inv.getId());
        dto.setTitle(inv.getTitle());
        dto.setDescription(inv.getDescription());
        dto.setStatus(inv.getStatus());
        dto.setCreatedAt(inv.getCreatedAt());
        dto.setUpdatedAt(inv.getUpdatedAt());
        return dto;
    }
}
