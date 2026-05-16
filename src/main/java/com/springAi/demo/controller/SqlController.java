package com.springAi.demo.controller;

import com.springAi.demo.model.ChatRequest;
import com.springAi.demo.model.SqlChatResponse;
import com.springAi.demo.service.SqlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class SqlController {

    private final SqlService sqlService;

    public SqlController(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    @GetMapping("/ordersInfo")
    public SqlChatResponse ask(@RequestBody ChatRequest prompt) {
        return sqlService.chatWithMyDatabase(prompt.getMessage());
    }
}
