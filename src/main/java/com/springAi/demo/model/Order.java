package com.springAi.demo.model;

import java.time.LocalDate;

public record Order(
        Long id,
        String customerName,
        String productName,
        Integer quantity,
        Double price,
        LocalDate orderDate,
        String status
) {
}