# Spring AI + Gemini + PostgreSQL Assistant

## Overview

This project is a Spring Boot application that converts natural language questions into optimized PostgreSQL `SELECT` queries using AI.

The application integrates:

- Spring Boot
- Spring AI
- Google Gemini
- PostgreSQL
- PGVector
- JDBC

The goal of the project is to allow users to interact with a PostgreSQL database using plain English while ensuring that generated SQL queries are safe, optimized, and restricted to read-only operations.

---

# Features

- Natural language to SQL conversion
- PostgreSQL query generation
- AI-powered query understanding using Gemini
- RAG (Retrieval-Augmented Generation) implementation using PGVector
- Context-aware query generation using vector embeddings
- Semantic search support for database understanding
- Vector storage using PGVector
- JDBC database integration
- Spring Boot REST APIs
- Safe SQL generation
- Read-only query enforcement

---

# Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring AI | AI Integration |
| Google Gemini | LLM Provider |
| PostgreSQL | Database |
| PGVector | Vector Storage |
| JDBC | Database Connectivity |
| Maven | Build Tool |
| Lombok | Boilerplate Reduction |

---

# Project Architecture

```text
Client Request
      ↓
REST Controller
      ↓
Service Layer
      ↓
Spring AI Prompt
      ↓
Gemini Model
      ↓
Generated PostgreSQL Query
      ↓
JDBC Execution
      ↓
Response to Client
```

---

# Database Schema

## Table: orders

| Column | Description |
|---|---|
| id | Unique order identifier |
| customer_name | Name of the customer |
| product_name | Product ordered |
| quantity | Quantity ordered |
| price | Product price |
| order_date | Date of order |
| status | Order status |

---

# Functional Flow

1. User sends a natural language query.
2. Spring AI sends the prompt to Gemini.
3. Gemini generates a PostgreSQL `SELECT` query.
4. Relevant schema/context is retrieved using RAG and embeddings.
5. The query is validated.
6. JDBC executes the query.
7. Results are returned to the user.

---

# Safety Rules

The AI assistant is configured with strict rules:

- Only `SELECT` statements are allowed
- No `DELETE`, `UPDATE`, `INSERT`, `DROP`, or `ALTER`
- Optimized SQL generation
- Safe query execution
- Controlled AI prompting

---

# Example Requests

## Example 1

### User Input

```text
Show all completed orders
```

### Generated SQL

```sql
SELECT *
FROM orders
WHERE status = 'COMPLETED';
```

---

## Example 2

### User Input

```text
Show orders where quantity is greater than 5
```

### Generated SQL

```sql
SELECT *
FROM orders
WHERE quantity > 5;
```

---

# API Endpoint

## Get Endpoint

```http
GET /ai/ordersInfo
```

---

# Maven Dependencies

Key dependencies used in the project:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-google-genai</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-vector-store-pgvector</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
<groupId>org.springframework.ai</groupId>
<artifactId>spring-ai-starter-model-google-genai-embedding</artifactId>
</dependency>
```

---

# Configuration

## application.properties

```properties
# Gemini API
spring.ai.google.genai.api-key=YOUR_API_KEY

# Embedding Model
spring.ai.google.genai.embedding.options.model=text-embedding-004

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres

# PGVector
spring.ai.vectorstore.pgvector.initialize-schema=true
```

---

# PostgreSQL Setup

## Enable PGVector Extension

Run this command in PostgreSQL:

```sql
CREATE EXTENSION IF NOT EXISTS vector;
```

---

# Running the Application

## Clone Repository

```bash
git clone <repository-url>
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

---

# Docker Support

Example PostgreSQL container:

```bash
docker compose up -d
```

---

# License

This project is intended for learning and development purposes.
