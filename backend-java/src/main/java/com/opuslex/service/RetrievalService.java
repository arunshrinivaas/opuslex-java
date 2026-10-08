package com.opuslex.service;

import com.opuslex.dto.rag.RagSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RetrievalService {

    private static final double DEFAULT_DISTANCE_THRESHOLD = 0.80;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final EmbeddingService embeddingService;

    public RetrievalService(NamedParameterJdbcTemplate jdbcTemplate, EmbeddingService embeddingService) {
        this.jdbcTemplate = jdbcTemplate;
        this.embeddingService = embeddingService;
    }

    public List<RagSource> searchSimilarChunks(String query, int limit, List<Integer> documentIds, Integer userId) {
        float[] queryEmbedding = embeddingService.generateEmbedding(query);
        String embeddingStr = "[" + IntStream.range(0, queryEmbedding.length)
                .mapToObj(i -> String.valueOf(queryEmbedding[i]))
                .collect(Collectors.joining(",")) + "]";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("query_embedding", embeddingStr)
                .addValue("limit", limit)
                .addValue("threshold", DEFAULT_DISTANCE_THRESHOLD);

        StringBuilder filterSql = new StringBuilder();
        if (userId != null) {
            filterSql.append(" AND d.user_id = :userId ");
            params.addValue("userId", userId);
        }

        if (documentIds != null) {
            if (documentIds.isEmpty()) {
                return List.of();
            }
            filterSql.append(" AND dc.document_id IN (:documentIds) ");
            params.addValue("documentIds", documentIds);
        }

        String sqlPass1 = "SELECT " +
                "dc.document_id, " +
                "d.title AS document_title, " +
                "d.filename AS document_filename, " +
                "dc.chunk_index, " +
                "dc.content, " +
                "dc.embedding <=> CAST(:query_embedding AS vector) AS distance " +
                "FROM document_chunks dc " +
                "JOIN documents d ON d.id = dc.document_id " +
                "WHERE dc.embedding IS NOT NULL " +
                "AND dc.embedding <=> CAST(:query_embedding AS vector) <= :threshold " +
                filterSql.toString() +
                "ORDER BY dc.embedding <=> CAST(:query_embedding AS vector) " +
                "LIMIT :limit";

        List<RagSource> pass1Results = executeQuery(sqlPass1, params);

        if (!pass1Results.isEmpty()) {
            return pass1Results;
        }

        // Pass 2: Fallback without threshold, only if scoped to investigation (documentIds provided)
        if (documentIds != null) {
            String sqlTopK = "SELECT " +
                    "dc.document_id, " +
                    "d.title AS document_title, " +
                    "d.filename AS document_filename, " +
                    "dc.chunk_index, " +
                    "dc.content, " +
                    "dc.embedding <=> CAST(:query_embedding AS vector) AS distance " +
                    "FROM document_chunks dc " +
                    "JOIN documents d ON d.id = dc.document_id " +
                    "WHERE dc.embedding IS NOT NULL " +
                    filterSql.toString() +
                    "ORDER BY dc.embedding <=> CAST(:query_embedding AS vector) " +
                    "LIMIT :limit";
            return executeQuery(sqlTopK, params);
        }

        return List.of();
    }

    private List<RagSource> executeQuery(String sql, MapSqlParameterSource params) {
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new RagSource(
                rs.getInt("document_id"),
                rs.getString("document_title"),
                rs.getString("document_filename"),
                rs.getInt("chunk_index"),
                rs.getDouble("distance"),
                rs.getString("content")
        ));
    }
}
