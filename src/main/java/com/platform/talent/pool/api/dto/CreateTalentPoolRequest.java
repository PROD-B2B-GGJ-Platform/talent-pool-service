package com.platform.talent.pool.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTalentPoolRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private String poolType;

    private List<String> tags;

    private Map<String, Object> criteria;

    private UUID ownerId;
}

