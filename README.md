# OpusLex

## Architecture

React + TypeScript + Vite
Java 21 + Spring Boot
PostgreSQL + pgvector

## Prerequisites

- Java 21
- Maven
- Node.js
- npm
- PostgreSQL
- pgvector

## Clone

```bash
git clone https://github.com/arunshrinivaas/opuslex-java.git
cd opuslex-java
```

## Environment Variables

Create `.env` in `backend-java/`:
```properties
JWT_SECRET_KEY=your_super_secret_jwt_key_that_is_at_least_256_bits_long
DATABASE_URL=jdbc:postgresql://localhost:5432/legal_compliance_db
DATABASE_USER=legal_app
DATABASE_PASSWORD=legal_app_password
```

Create `.env` in `frontend/`:
```properties
VITE_API_URL=http://localhost:8080/api/v1
```

## Database Setup

```bash
# Connect to PostgreSQL as superuser
psql -U postgres
```

```sql
CREATE DATABASE legal_compliance_db;
CREATE USER legal_app WITH PASSWORD 'legal_app_password';
GRANT ALL PRIVILEGES ON DATABASE legal_compliance_db TO legal_app;
\c legal_compliance_db
CREATE EXTENSION IF NOT EXISTS vector;
```

## Backend Startup

```bash
cd backend-java
export JWT_SECRET_KEY="your_super_secret_jwt_key_that_is_at_least_256_bits_long"
export DATABASE_URL="jdbc:postgresql://localhost:5432/legal_compliance_db"
export DATABASE_USER="legal_app"
export DATABASE_PASSWORD="legal_app_password"
mvn spring-boot:run
```

## Frontend Startup

```bash
cd frontend
npm install
npm run dev
```

## Presentation Flow

1. Start PostgreSQL
2. Start Java backend
3. Start React frontend
4. Login
5. Open dashboard
6. Open investigation
7. Open documents
8. Demonstrate RAG
9. Show evidence/retrieval

## Troubleshooting

- **Vector extension missing:** Ensure `pgvector` is installed on your OS and the `CREATE EXTENSION vector;` command was successful before starting the backend.
