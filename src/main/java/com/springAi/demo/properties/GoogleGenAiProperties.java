package com.springAi.demo.properties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.ai.google.genai")
public class GoogleGenAiProperties {

    private String apiKey;

    private String projectId;

    private String model;

    private Integer embeddingDimensions;

    private String embeddingModel;

    private Chat chat = new Chat();

    private Embedding embedding = new Embedding();

    @Data
    public static class Chat {

        private Options options = new Options();

        @Data
        public static class Options {

            private String model;
        }
    }

    @Data
    public static class Embedding {

        private Options options = new Options();

        @Data
        public static class Options {

            private String model;
        }
    }
}