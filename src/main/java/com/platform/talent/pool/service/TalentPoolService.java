package com.platform.talent.pool.service;

import com.platform.talent.pool.api.dto.CreateTalentPoolRequest;
import com.platform.talent.pool.api.dto.TalentPoolResponse;
import com.platform.talent.pool.domain.model.TalentPool;
import com.platform.talent.pool.domain.model.TalentPoolMember;
import com.platform.talent.pool.domain.repository.TalentPoolMemberRepository;
import com.platform.talent.pool.domain.repository.TalentPoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TalentPoolService {

    private final TalentPoolRepository poolRepository;
    private final TalentPoolMemberRepository memberRepository;

    @Transactional
    public TalentPoolResponse createPool(UUID tenantId, CreateTalentPoolRequest request) {
        log.info("Creating talent pool: {}", request.getName());

        TalentPool pool = TalentPool.builder()
                .tenantId(tenantId)
                .name(request.getName())
                .description(request.getDescription())
                .poolType(request.getPoolType())
                .tags(request.getTags())
                .criteria(request.getCriteria())
                .ownerId(request.getOwnerId())
                .isActive(true)
                .memberCount(0)
                .build();

        pool = poolRepository.save(pool);
        return mapToResponse(pool);
    }

    @Transactional
    public void addCandidateToPool(UUID tenantId, UUID poolId, UUID candidateId, UUID addedBy) {
        log.info("Adding candidate {} to pool {}", candidateId, poolId);

        TalentPool pool = poolRepository.findByIdAndTenantId(poolId, tenantId)
                .orElseThrow(() -> new RuntimeException("Pool not found"));

        if (memberRepository.existsByPoolIdAndCandidateId(poolId, candidateId)) {
            throw new RuntimeException("Candidate already in pool");
        }

        TalentPoolMember member = TalentPoolMember.builder()
                .tenantId(tenantId)
                .poolId(poolId)
                .candidateId(candidateId)
                .addedBy(addedBy)
                .addedAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        // Update member count
        pool.setMemberCount(pool.getMemberCount() + 1);
        poolRepository.save(pool);

        log.info("Candidate added to pool successfully");
    }

    @Transactional
    public void removeCandidateFromPool(UUID tenantId, UUID poolId, UUID candidateId) {
        TalentPool pool = poolRepository.findByIdAndTenantId(poolId, tenantId)
                .orElseThrow(() -> new RuntimeException("Pool not found"));

        memberRepository.deleteByPoolIdAndCandidateId(poolId, candidateId);

        pool.setMemberCount(Math.max(0, pool.getMemberCount() - 1));
        poolRepository.save(pool);
    }

    @Transactional(readOnly = true)
    public Page<TalentPoolResponse> listPools(UUID tenantId, Pageable pageable) {
        return poolRepository.findByTenantId(tenantId, pageable)
                .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public List<UUID> getPoolMembers(UUID tenantId, UUID poolId) {
        poolRepository.findByIdAndTenantId(poolId, tenantId)
                .orElseThrow(() -> new RuntimeException("Pool not found"));

        return memberRepository.findByPoolId(poolId).stream()
                .map(TalentPoolMember::getCandidateId)
                .toList();
    }

    private TalentPoolResponse mapToResponse(TalentPool pool) {
        return TalentPoolResponse.builder()
                .id(pool.getId())
                .tenantId(pool.getTenantId())
                .name(pool.getName())
                .description(pool.getDescription())
                .poolType(pool.getPoolType())
                .tags(pool.getTags())
                .memberCount(pool.getMemberCount())
                .isActive(pool.getIsActive())
                .createdAt(pool.getCreatedAt())
                .build();
    }
}

