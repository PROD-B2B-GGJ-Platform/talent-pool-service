package com.platform.talent.pool.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TalentPoolResponse {
    private UUID id;
    private UUID tenantId;
    private String name;
    private String description;
    private String poolType;
    private List<String> tags;
    private Integer memberCount;
    private Boolean isActive;
    private LocalDateTime createdAt;
}

