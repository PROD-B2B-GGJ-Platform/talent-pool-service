CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE ggj_talent_pools (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    pool_type VARCHAR(50),
    tags JSONB,
    criteria JSONB,
    is_active BOOLEAN DEFAULT TRUE,
    member_count INTEGER DEFAULT 0,
    owner_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    version BIGINT DEFAULT 0
);

CREATE TABLE ggj_talent_pool_members (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id UUID NOT NULL,
    pool_id UUID NOT NULL,
    candidate_id UUID NOT NULL,
    notes TEXT,
    added_by UUID,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pool_id) REFERENCES ggj_talent_pools(id) ON DELETE CASCADE,
    CONSTRAINT uq_pool_candidate UNIQUE (pool_id, candidate_id)
);

CREATE INDEX idx_pool_tenant ON ggj_talent_pools(tenant_id);
CREATE INDEX idx_pool_type ON ggj_talent_pools(pool_type);
CREATE INDEX idx_member_pool ON ggj_talent_pool_members(pool_id);
CREATE INDEX idx_member_candidate ON ggj_talent_pool_members(candidate_id);

