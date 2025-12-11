package com.platform.talent.pool.domain.repository;

import com.platform.talent.pool.domain.model.TalentPoolMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TalentPoolMemberRepository extends JpaRepository<TalentPoolMember, UUID> {

    List<TalentPoolMember> findByPoolId(UUID poolId);

    List<TalentPoolMember> findByCandidateId(UUID candidateId);

    Optional<TalentPoolMember> findByPoolIdAndCandidateId(UUID poolId, UUID candidateId);

    boolean existsByPoolIdAndCandidateId(UUID poolId, UUID candidateId);

    long countByPoolId(UUID poolId);

    void deleteByPoolIdAndCandidateId(UUID poolId, UUID candidateId);
}

