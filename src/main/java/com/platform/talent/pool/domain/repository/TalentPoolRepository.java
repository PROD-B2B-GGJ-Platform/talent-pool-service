package com.platform.talent.pool.domain.repository;

import com.platform.talent.pool.domain.model.TalentPool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TalentPoolRepository extends JpaRepository<TalentPool, UUID> {

    Optional<TalentPool> findByIdAndTenantId(UUID id, UUID tenantId);

    Page<TalentPool> findByTenantId(UUID tenantId, Pageable pageable);

    List<TalentPool> findByTenantIdAndIsActiveTrue(UUID tenantId);

    Page<TalentPool> findByTenantIdAndPoolType(UUID tenantId, String poolType, Pageable pageable);
}

