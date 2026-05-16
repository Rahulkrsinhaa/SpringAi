package com.springAi.demo.model;

public record SqlChatResponse(
        String userQuestion,
        String retrievedContext,
        String llmResponse
) {
}