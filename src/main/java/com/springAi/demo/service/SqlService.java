package com.springAi.demo.service;

import com.springAi.demo.model.SqlChatResponse;
import com.springAi.demo.tool.SqlTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SqlService {

    private final ChatClient chatClient;
    private final SqlTool sqlTool;
    private final PromptLoader promptLoader;
    private final VectorStore vectorStore;

    public SqlService(ChatClient chatClient,
                      SqlTool sqlTool,
                      PromptLoader promptLoader,
                      VectorStore vectorStore) {

        this.chatClient = chatClient;
        this.sqlTool = sqlTool;
        this.promptLoader = promptLoader;
        this.vectorStore = vectorStore;
    }

    public SqlChatResponse chatWithMyDatabase(String message) {

        // Step 1: Retrieve schema/business context
        List<Document> documents =
                vectorStore.similaritySearch(message);

        // Step 2: Convert docs into context string
        String context = documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));

        // Step 3: Ask LLM
        String response = chatClient.prompt()
                .system("""
                        %s
                        
                        Use the provided context while generating SQL queries.
                        
                        Context:
                        %s
                        """.formatted(
                        promptLoader.loadSqlPrompt(),
                        context
                ))
                .user(message)
                .tools(sqlTool)
                .call()
                .content();

        // Step 4: Return everything
        return new com.springAi.demo.model.SqlChatResponse(
                message,
                context,
                response
        );
    }
}