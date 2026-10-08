# OpusLex — Java / Spring Boot

## Project Overview
OpusLex is a legal and compliance research assistant. This repository contains the active Java 21 + Spring Boot implementation, migrated from the original Python/FastAPI architecture.

**Note:** The original Python/FastAPI implementation is archived separately and is NOT required to run this version.

## Architecture
- **Frontend**: React + TypeScript + Vite
- **Backend**: Java 21 + Spring Boot 3.x
- **Security**: Spring Security + JWT, Email/Phone OTP, Google Auth, TOTP/2FA
- **Database**: PostgreSQL with pgvector for embeddings
- **AI/RAG**: Spring AI, DJL + local ONNX embedding model, AI Agent, MCP (Model Context Protocol)

## Local Development Setup

### Required Environment Variables
Create a `.env` file in `backend-java/` with the following variables:
```
DB_URL=jdbc:postgresql://localhost:5432/opuslex
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password
JWT_SECRET_KEY=your_base64_jwt_secret
OPENAI_API_KEY=your_openai_key
TWILIO_ACCOUNT_SID=your_twilio_sid
TWILIO_AUTH_TOKEN=your_twilio_token
GOOGLE_CLIENT_ID=your_google_client_id
```

### Running Backend
```bash
cd backend-java
mvn clean install
mvn spring-boot:run
```

### Running Frontend
```bash
cd frontend
npm install
npm run dev
```

### Running Tests
```bash
cd backend-java
mvn clean test
```
