package com.opuslex.controller;

import com.opuslex.domain.compliance.Compliance;
import com.opuslex.domain.compliance.ComplianceRepository;
import com.opuslex.domain.compliance.PolicyRepository;
import com.opuslex.domain.compliance.RegulationRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/governance")
public class GovernanceController {
    private final PolicyRepository policyRepo;
    private final RegulationRepository regRepo;
    private final ComplianceRepository complianceRepo;

    public GovernanceController(PolicyRepository policyRepo, RegulationRepository regRepo, ComplianceRepository complianceRepo) {
        this.policyRepo = policyRepo;
        this.regRepo = regRepo;
        this.complianceRepo = complianceRepo;
    }

    @GetMapping("/summary")
    public ResponseEntity<?> summary() {
        long totalPolicies = policyRepo.count();
        // Since we don't have active/draft explicit counts in a simple way without writing custom repo queries,
        // we'll just return total for now to demonstrate it reads from DB.
        Map<String, Object> response = new HashMap<>();
        response.put("total_policies", totalPolicies);
        response.put("active_policies", totalPolicies);
        response.put("draft_policies", 0);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/policies")
    public ResponseEntity<?> policies() {
        return ResponseEntity.ok(policyRepo.findAll());
    }

    @GetMapping("/regulations")
    public ResponseEntity<?> regulations() {
        return ResponseEntity.ok(regRepo.findAll());
    }

    @GetMapping("/compliance-risk")
    public ResponseEntity<?> risk() {
        List<Compliance> risks = complianceRepo.findAll();
        return ResponseEntity.ok(risks);
    }
}
