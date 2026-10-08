package com.opuslex.service;

import com.opuslex.domain.investigation.Investigation;
import com.opuslex.domain.investigation.InvestigationDocument;
import com.opuslex.domain.investigation.InvestigationDocumentRepository;
import com.opuslex.domain.investigation.InvestigationRepository;
import com.opuslex.dto.rag.RagRequest;
import com.opuslex.dto.rag.RagResponse;
import com.opuslex.dto.rag.RagSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RagService {

    private final RetrievalService retrievalService;
    private final InvestigationRepository investigationRepository;
    private final InvestigationDocumentRepository investigationDocumentRepository;

    public RagService(RetrievalService retrievalService,
                      InvestigationRepository investigationRepository,
                      InvestigationDocumentRepository investigationDocumentRepository) {
        this.retrievalService = retrievalService;
        this.investigationRepository = investigationRepository;
        this.investigationDocumentRepository = investigationDocumentRepository;
    }

    @Transactional(readOnly = true)
    public RagResponse askRag(RagRequest request, Integer userId) {
        List<Integer> documentIds = null;

        if (request.getInvestigationId() != null) {
            Investigation investigation = investigationRepository.findById(request.getInvestigationId())
                    .orElseThrow(() -> new com.opuslex.exception.ResourceNotFoundException("Investigation not found"));

            if (!investigation.getUserId().equals(userId)) {
                throw new com.opuslex.exception.ResourceNotFoundException("Investigation not found");
            }

            List<InvestigationDocument> invDocs = investigationDocumentRepository.findByInvestigationId(request.getInvestigationId());
            documentIds = invDocs.stream().map(InvestigationDocument::getDocumentId).collect(Collectors.toList());

            if (documentIds.isEmpty()) {
                return new RagResponse(
                        request.getQuestion(),
                        "No documents are attached to this investigation.",
                        List.of()
                );
            }
        }

        List<RagSource> sources = retrievalService.searchSimilarChunks(
                request.getQuestion(),
                request.getLimit(),
                documentIds,
                userId
        );

        if (request.getInvestigationId() != null && sources.isEmpty()) {
            return new RagResponse(
                    request.getQuestion(),
                    "No relevant evidence was found in the documents currently attached to this investigation. The documents may not yet be fully processed (embedded), or the question may not match the available content.",
                    List.of()
            );
        }

        // Mocking LLM due to environment-specific Copilot integration blocker
        String answer = buildMockAnswer(sources);

        return new RagResponse(request.getQuestion(), answer, sources);
    }

    private String buildMockAnswer(List<RagSource> sources) {
        if (sources.isEmpty()) {
            return "[Copilot LLM Blocker] No sources retrieved, and LLM generation is unavailable.";
        }
        return "[Copilot LLM Blocker] Retrieved " + sources.size() + " chunk(s) from " + 
               sources.stream().map(RagSource::getFilename).distinct().count() + 
               " document(s). LLM synthesis is not implemented.";
    }
}
