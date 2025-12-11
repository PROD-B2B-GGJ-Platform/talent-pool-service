# Talent Pool Service - Design Document Specification (DDS)

## Document Information

| Field | Value |
|-------|-------|
| Version | 10.0.0.1 |
| Last Updated | 2025-12-11 |
| Status | Approved |
| Owner | Talent & Recruitment Team |

---

## 1. Overview

The Talent Pool Service manages candidate pools for talent segmentation, enabling organizations to build and nurture pipelines of qualified candidates for future hiring needs.

### 1.1 Key Features

- Talent pool CRUD operations
- Candidate pool membership management
- Tag-based segmentation
- Pool analytics and reporting
- Automated candidate recommendations

---

## 2. Architecture

### 2.1 Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| Runtime | Java | 21+ |
| Framework | Spring Boot | 3.2.0 |
| Database | PostgreSQL | 15+ |
| Port | 8094 | |

---

## 3. Data Model

### 3.1 Database Schema

#### Table: talent_pools

| Column | Type | Description |
|--------|------|-------------|
| id | UUID | PRIMARY KEY |
| organization_id | UUID | Multi-tenant isolation |
| name | VARCHAR(255) | Pool name |
| description | TEXT | Pool description |
| pool_type | VARCHAR(50) | ACTIVE, PASSIVE, SILVER_MEDALIST, REFERRAL, ALUMNI |
| tags | TEXT[] | Segmentation tags |
| member_count | INTEGER | Number of candidates |
| created_at | TIMESTAMP | Creation time |

#### Table: pool_members

| Column | Type | Description |
|--------|------|-------------|
| id | UUID | PRIMARY KEY |
| pool_id | UUID | FK to talent_pools |
| candidate_id | UUID | FK to candidates |
| added_by | UUID | User who added |
| notes | TEXT | Notes about member |
| added_at | TIMESTAMP | When added |

---

## 4. API Design

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/talent-pools` | List pools |
| POST | `/api/v1/talent-pools` | Create pool |
| GET | `/api/v1/talent-pools/{id}` | Get pool |
| PUT | `/api/v1/talent-pools/{id}` | Update pool |
| DELETE | `/api/v1/talent-pools/{id}` | Delete pool |
| POST | `/api/v1/talent-pools/{id}/members` | Add member |
| DELETE | `/api/v1/talent-pools/{id}/members/{candidateId}` | Remove member |
| GET | `/api/v1/talent-pools/{id}/members` | List members |

---

## 5. Pool Types

| Type | Description |
|------|-------------|
| ACTIVE | Actively seeking candidates |
| PASSIVE | Not actively seeking but open |
| SILVER_MEDALIST | Previous finalists not hired |
| REFERRAL | Employee referrals |
| ALUMNI | Former employees |
| CUSTOM | Custom pool type |

---

## 6. References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

