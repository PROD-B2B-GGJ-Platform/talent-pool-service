# Talent Pool Service - Help Guide

## Quick Start

```bash
git clone https://github.com/PROD-B2B-GGJ-Platform/talent-pool-service.git
cd talent-pool-service

export DB_HOST=localhost
mvn spring-boot:run
```

Access: http://localhost:8094/swagger-ui.html

---

## API Examples

### Create Pool

```bash
curl -X POST http://localhost:8094/api/v1/talent-pools \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Senior Engineers Pool",
    "description": "Pre-screened senior engineers",
    "poolType": "ACTIVE",
    "tags": ["engineering", "senior", "pre-screened"]
  }'
```

### Add Member

```bash
curl -X POST http://localhost:8094/api/v1/talent-pools/{poolId}/members \
  -H "Content-Type: application/json" \
  -d '{
    "candidateId": "candidate-uuid",
    "notes": "Excellent Java background, available in 2 weeks"
  }'
```

### List Members

```bash
curl http://localhost:8094/api/v1/talent-pools/{poolId}/members
```

---

## Pool Types

| Type | Use Case |
|------|----------|
| ACTIVE | Active job seekers |
| PASSIVE | Passive candidates |
| SILVER_MEDALIST | Previous finalists |
| REFERRAL | Employee referrals |
| ALUMNI | Former employees |

---

## Support

GitHub: https://github.com/PROD-B2B-GGJ-Platform/talent-pool-service

