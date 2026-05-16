package com.springAi.demo.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SqlTool  {

    private final JdbcTemplate jdbcTemplate;

    public SqlTool(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Tool(description = "Execute PostgreSQL SELECT queries")
    public String executeQuery(String sql) throws Exception {

        String normalized = sql.trim();
        String lowerCaseSql = normalized.toLowerCase();

        // validate using lowercase copy
        if (!lowerCaseSql.startsWith("select")) {
            throw new RuntimeException("Only SELECT queries are allowed");
        }

        if (lowerCaseSql.contains("delete") ||
                lowerCaseSql.contains("update") ||
                lowerCaseSql.contains("insert") ||
                lowerCaseSql.contains("drop") ||
                lowerCaseSql.contains("alter")) {

            throw new RuntimeException("Dangerous query blocked");
        }

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        return objectMapper.writeValueAsString(result);
    }
}
