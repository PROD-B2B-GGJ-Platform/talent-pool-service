package com.platform.talent.pool.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ggj_talent_pool_members", indexes = {
    @Index(name = "idx_member_pool", columnList = "pool_id"),
    @Index(name = "idx_member_candidate", columnList = "candidate_id"),
    @Index(name = "idx_member_pool_candidate", columnList = "pool_id, candidate_id", unique = true)
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TalentPoolMember {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "pool_id", nullable = false)
    private UUID poolId;

    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "added_by")
    private UUID addedBy;

    @CreatedDate
    @Column(name = "added_at")
    private LocalDateTime addedAt;
}

