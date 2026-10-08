CREATE EXTENSION IF NOT EXISTS vector;

CREATE INDEX IF NOT EXISTS ix_document_chunks_embedding 
ON document_chunks USING hnsw (embedding vector_cosine_ops) 
WITH (m = 16, ef_construction = 64);
