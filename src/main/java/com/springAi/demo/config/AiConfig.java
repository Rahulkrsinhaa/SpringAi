package com.springAi.demo.config;

import com.springAi.demo.properties.GoogleGenAiProperties;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiEmbeddingConnectionDetails;
import org.springframework.ai.google.genai.text.GoogleGenAiTextEmbeddingModel;
import org.springframework.ai.google.genai.text.GoogleGenAiTextEmbeddingOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(GoogleGenAiProperties.class)
public class AiConfig {
    @Bean
    public ChatClient geminiChatClient(GoogleGenAiChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

    @Bean
    @Primary
    public GoogleGenAiEmbeddingConnectionDetails googleGenAiConnectionDetails(GoogleGenAiProperties properties) {
        return GoogleGenAiEmbeddingConnectionDetails.builder()
                .apiKey(properties.getApiKey())
                .projectId(properties.getProjectId())
                .build();
    }

    @Bean
    @Primary
    public EmbeddingModel googleEmbeddingModel(GoogleGenAiEmbeddingConnectionDetails connectionDetails, GoogleGenAiProperties googleGenAiProperties) {
        GoogleGenAiEmbeddingConnectionDetails client = GoogleGenAiEmbeddingConnectionDetails.builder()
                .apiKey(connectionDetails.getApiKey())
                .build();

        var options = GoogleGenAiTextEmbeddingOptions.builder()
                .model(googleGenAiProperties.getModel())
                .dimensions(googleGenAiProperties.getEmbeddingDimensions())
                .build();

        return new GoogleGenAiTextEmbeddingModel(client, options);
    }
}
