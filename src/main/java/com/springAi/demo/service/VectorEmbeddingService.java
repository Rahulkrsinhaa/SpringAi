package com.springAi.demo.service;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.vectorstore.SearchRequest;

@Service
public class VectorEmbeddingService {

    private final VectorStore vectorStore;

    public VectorEmbeddingService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void embedSchemaDocument() {

        // Check existing embeddings
        List<Document> existingDocs =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query("orders table")
                                .topK(1)
                                .build()
                );

        // Skip if already embedded
        if (!existingDocs.isEmpty()) {
            return;
        }

        TextReader reader = new TextReader(
                new ClassPathResource("docs/Orders-Rag-Embedding-Documentation.docx")
        );

        List<Document> documents = reader.get();

        // Add embeddings
        vectorStore.add(documents);
    }
}