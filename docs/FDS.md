# Talent Pool Service - Functional Design Specification (FDS)

## Document Information

| Field | Value |
|-------|-------|
| Version | 10.0.0.1 |
| Last Updated | 2025-12-11 |
| Status | Approved |

---

## 1. Functional Requirements

### FR-001: Create Talent Pool

Create a new talent pool with name, description, type, and tags.

**Input:**
- name (required)
- description
- poolType (ACTIVE, PASSIVE, SILVER_MEDALIST, REFERRAL, ALUMNI)
- tags[]

**Output:** Created pool with ID

---

### FR-002: Add Candidate to Pool

Add an existing candidate to a talent pool.

**Business Rules:**
- Candidate can be in multiple pools
- Duplicate membership prevented
- Notes optional for each membership

---

### FR-003: Remove Candidate from Pool

Remove a candidate from a specific pool.

---

### FR-004: Search Pool Members

Search candidates within a pool with filters.

**Filters:**
- keyword
- skills
- location
- addedAfter, addedBefore

---

### FR-005: Pool Analytics

Get analytics for a talent pool:
- Member count
- Growth over time
- Skill distribution
- Availability breakdown

---

## 2. Use Cases

### Silver Medalist Tracking

Candidates who reached final rounds but weren't selected are automatically added to Silver Medalist pool for future opportunities.

### Alumni Network

Former employees can be tracked and engaged for potential rehiring.

### Referral Pipeline

Employee referrals tracked separately with referrer information.

---

## 3. Acceptance Criteria

- [ ] Pools created with unique names per org
- [ ] Candidates added/removed from pools
- [ ] Member count updated automatically
- [ ] Tags support filtering
- [ ] Analytics endpoint returns correct data

