package com.opuslex.service;

import com.opuslex.component.DocumentChunker;
import com.opuslex.component.PdfExtractor;
import com.opuslex.domain.document.Document;
import com.opuslex.domain.document.DocumentChunk;
import com.opuslex.domain.document.DocumentChunkRepository;
import com.opuslex.domain.document.DocumentRepository;
import com.opuslex.dto.DocumentDto;
import com.opuslex.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DocumentService {
    private final DocumentRepository repository;
    private final DocumentChunkRepository chunkRepository;
    private final PdfExtractor pdfExtractor;
    private final DocumentChunker chunker;
    private final EmbeddingService embeddingService;

    public DocumentService(DocumentRepository repository, 
                           DocumentChunkRepository chunkRepository,
                           PdfExtractor pdfExtractor, 
                           DocumentChunker chunker, 
                           EmbeddingService embeddingService) {
        this.repository = repository;
        this.chunkRepository = chunkRepository;
        this.pdfExtractor = pdfExtractor;
        this.chunker = chunker;
        this.embeddingService = embeddingService;
    }
    
    public List<DocumentDto> listDocuments(Integer userId) {
        return repository.findByUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }
    
    public DocumentDto getDocument(Integer id, Integer userId) {
        return repository.findByIdAndUserId(id, userId).map(this::mapToDto)
            .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }

    @Transactional
    public DocumentDto uploadDocument(MultipartFile file, String title, Integer userId) {
        String extractedText;
        try {
            extractedText = pdfExtractor.extractTextFromPdf(file.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read PDF file", e);
        }

        Document doc = new Document();
        doc.setUserId(userId);
        doc.setFilename(file.getOriginalFilename());
        doc.setTitle(title != null ? title : file.getOriginalFilename());
        doc.setDocumentType("pdf");
        doc.setJurisdiction("Unknown");
        doc.setExtractedText(extractedText);
        doc.setUploadedAt(LocalDateTime.now());
        
        // Save the document first to get the ID
        doc = repository.save(doc);

        List<String> textChunks = chunker.chunkText(extractedText);
        int index = 0;
        for (String textChunk : textChunks) {
            float[] embedding = embeddingService.generateEmbedding(textChunk);
            DocumentChunk chunk = new DocumentChunk();
            chunk.setDocumentId(doc.getId());
            chunk.setChunkIndex(index++);
            chunk.setContent(textChunk);
            chunk.setEmbedding(embedding);
            chunk.setCreatedAt(LocalDateTime.now());
            chunkRepository.save(chunk);
        }

        return mapToDto(doc);
    }
    
    private DocumentDto mapToDto(Document doc) {
        DocumentDto dto = new DocumentDto();
        dto.setId(doc.getId());
        dto.setFilename(doc.getFilename());
        dto.setTitle(doc.getTitle());
        dto.setJurisdiction(doc.getJurisdiction());
        dto.setCreatedAt(doc.getUploadedAt());
        return dto;
    }
}
