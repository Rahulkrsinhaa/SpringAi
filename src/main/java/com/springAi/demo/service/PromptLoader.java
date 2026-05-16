package com.springAi.demo.service;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class PromptLoader {

    public String loadSqlPrompt() {

        PromptTemplate template = new PromptTemplate(
                new ClassPathResource("prompts/sql-system-prompt.st")
        );
        return template.render();
    }
}