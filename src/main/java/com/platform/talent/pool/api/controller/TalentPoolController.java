package com.platform.talent.pool.api.controller;

import com.platform.talent.pool.api.dto.CreateTalentPoolRequest;
import com.platform.talent.pool.api.dto.TalentPoolResponse;
import com.platform.talent.pool.service.TalentPoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/talent-pools")
@RequiredArgsConstructor
@Tag(name = "Talent Pool", description = "Talent pool management API")
public class TalentPoolController {

    private final TalentPoolService poolService;

    @PostMapping
    @Operation(summary = "Create talent pool")
    public ResponseEntity<TalentPoolResponse> createPool(
            @RequestHeader("X-Tenant-ID") UUID tenantId,
            @Valid @RequestBody CreateTalentPoolRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(poolService.createPool(tenantId, request));
    }

    @GetMapping
    @Operation(summary = "List talent pools")
    public ResponseEntity<Page<TalentPoolResponse>> listPools(
            @RequestHeader("X-Tenant-ID") UUID tenantId,
            Pageable pageable) {
        return ResponseEntity.ok(poolService.listPools(tenantId, pageable));
    }

    @PostMapping("/{poolId}/members/{candidateId}")
    @Operation(summary = "Add candidate to pool")
    public ResponseEntity<Void> addCandidate(
            @RequestHeader("X-Tenant-ID") UUID tenantId,
            @PathVariable UUID poolId,
            @PathVariable UUID candidateId,
            @RequestHeader("X-User-ID") UUID userId) {
        poolService.addCandidateToPool(tenantId, poolId, candidateId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{poolId}/members/{candidateId}")
    @Operation(summary = "Remove candidate from pool")
    public ResponseEntity<Void> removeCandidate(
            @RequestHeader("X-Tenant-ID") UUID tenantId,
            @PathVariable UUID poolId,
            @PathVariable UUID candidateId) {
        poolService.removeCandidateFromPool(tenantId, poolId, candidateId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{poolId}/members")
    @Operation(summary = "Get pool members")
    public ResponseEntity<List<UUID>> getMembers(
            @RequestHeader("X-Tenant-ID") UUID tenantId,
            @PathVariable UUID poolId) {
        return ResponseEntity.ok(poolService.getPoolMembers(tenantId, poolId));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "talent-pool-service",
            "version", "10.0.0.1"
        ));
    }
}

