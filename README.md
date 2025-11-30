# API Completa com Spring Boot

API RESTful completa para gerenciamento de produtos com autenticação JWT, proteção de rotas e containerização Docker. Desenvolvida com Spring Boot e PostgreSQL seguindo boas práticas de arquitetura.

[Funcionalidades](#-funcionalidades) • [Tecnologias](#-tecnologias) • [Instalação](#-instalação) • [API](#-documentação-da-api) • [Docker](#-docker)

---

## 📑 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Instalação](#-instalação)
- [Docker](#-docker)
- [Documentação da API](#-documentação-da-api)
- [Autenticação JWT](#-autenticação-jwt)
- [Configuração](#-configuração)
- [Contribuindo](#-contribuindo)
- [Licença](#-licença)

---

## Sobre o Projeto

Esta é uma API RESTful robusta construída com **Spring Boot** e **PostgreSQL**, seguindo boas práticas de arquitetura, tratamento de exceções e autenticação com **JWT (JSON Web Token)**. O projeto foi desenvolvido com foco em segurança, escalabilidade e facilidade de deploy através de containerização Docker.

### Principais Características

- **Autenticação JWT** - Sistema completo de login e proteção de rotas
- **Gestão de Produtos** - CRUD completo com validações
- **Gestão de Usuários** - Registro e gerenciamento de usuários
- **Controle de Acesso** - Proteção de rotas sensíveis
- **Tratamento de Exceções** - Handler global para erros
- **Containerizado** - Deploy fácil com Docker e Docker Compose
- **Banco PostgreSQL** - Persistência robusta e confiável

---

## 🚀 Funcionalidades

### Backend (API REST)

#### Autenticação

- Registro de novos usuários
- Login com JWT
- Proteção de rotas com tokens
- Validação e renovação de tokens

#### Produtos

- Criar, editar, listar e remover produtos
- Validações de campos obrigatórios
- Proteção de endpoints (requer autenticação)
- Tratamento de erros personalizados

#### Usuários

- Cadastro de usuários
- Autenticação segura
- Controle de acesso por roles
- Validação de credenciais

---

## 🛠️ Tecnologias

### Backend

| Tecnologia      | Versão | Descrição                     |
| --------------- | ------ | ----------------------------- |
| Java            | 17     | Linguagem de programação      |
| Spring Boot     | 3.5.3  | Framework backend             |
| Spring Security | 6.4.5  | Autenticação e autorização    |
| Spring Data JPA | 3.5.8  | Persistência de dados         |
| JWT (jjwt)      | 0.11.5 | Tokens de autenticação        |
| PostgreSQL      | 16+    | Banco de dados                |
| Maven           | 3.9+   | Gerenciamento de dependências |

### DevOps

- **Docker** - Containerização
- **Docker Compose** - Orquestração de containers
- **PostgreSQL Alpine** - Banco de dados otimizado

---

## 🏗️ Arquitetura

### Backend - Estrutura de Pacotes

```
src/main/java/com/example/api_completa_com_spring_boot/
├── controller/          # Endpoints REST
│   ├── AuthController.java
│   └── ProdutoController.java
├── model/              # Entidades JPA
│   ├── Produto.java
│   └── Usuario.java
├── repository/         # Interfaces de persistência
│   ├── ProdutoRepository.java
│   └── UsuarioRepository.java
├── service/            # Lógica de negócios
│   ├── ProdutoService.java
│   ├── UsuarioService.java
│   └── UsuarioDetailService.java
├── security/           # Configurações de segurança e JWT
│   ├── SecurityConfig.java
│   ├── JwtUtil.java
│   └── JwtAuthFilter.java
└── exception/          # Tratamento de exceções
    ├── GlobalExceptionHandler.java
    └── RecursoNaoEncontradoException.java
```

### Banco de Dados - Modelo de Dados

```
┌─────────────┐       ┌─────────────┐
│   Produto   │       │   Usuario   │
├─────────────┤       ├─────────────┤
│ id          │       │ id          │
│ nome        │       │ username    │
│ descricao   │       │ password    │
│ preco       │       │ roles       │
│ quantidade  │       │ enabled     │
│ createdAt   │       │ createdAt   │
│ updatedAt   │       │ updatedAt   │
└─────────────┘       └─────────────┘
```

---

## 📥 Instalação

### Pré-requisitos

- **Docker** - [Download](https://www.docker.com/)
- **Docker Compose** - Incluído no Docker Desktop

### Instalação com Docker (Recomendado)

#### 1. Clone o repositório

```bash
git clone https://github.com/nevesmarcos42/API-Completa-com-Spring-Boot.git
cd API-Completa-com-Spring-Boot
```

#### 2. Inicie a aplicação

```bash
docker-compose up -d
```

✅ **Pronto!** A aplicação estará rodando em:

- **Backend API**: `http://localhost:8080`
- **PostgreSQL**: `localhost:5432`

#### 3. Verificar status dos containers

```bash
docker-compose ps
```

#### 4. Ver logs da aplicação

```bash
docker-compose logs -f app
```

#### 5. Parar a aplicação

```bash
docker-compose down
```

#### 6. Parar e remover volumes (limpar dados)

```bash
docker-compose down -v
```

---

## 🐳 Docker

### Arquitetura Docker

A aplicação é composta por 2 containers:

1. **postgres-db** - PostgreSQL 16
2. **spring-boot-app** - API Spring Boot (Java 17)

### Volumes

- `postgres_data` - Persistência do banco de dados

### Network

- `app-network` - Comunicação entre containers

### Dockerfile

O projeto utiliza build multi-estágio para otimizar a imagem:

```dockerfile
# Estágio 1: Build com Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Execução com JRE Alpine
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose

```yaml
services:
  postgres:
    image: postgres:16-alpine
    environment:
      POSTGRES_DB: meubanco
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: F@53a6!lP9w0rd$2#
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]

  app:
    build: .
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/meubanco
    depends_on:
      postgres:
        condition: service_healthy
    ports:
      - "8080:8080"
```

---

## 📚 Documentação da API

### Principais Endpoints

#### Autenticação

```http
POST /auth/register    # Registrar novo usuário
POST /auth/login       # Login e obter token JWT
```

#### Produtos

```http
GET    /produtos           # Listar todos os produtos (autenticado)
POST   /produtos           # Criar produto (autenticado)
GET    /produtos/{id}      # Buscar produto por ID (autenticado)
PUT    /produtos/{id}      # Atualizar produto (autenticado)
DELETE /produtos/{id}      # Deletar produto (autenticado)
```

### Exemplos de Requisições

#### Registrar Usuário

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario",
    "password": "senha123"
  }'
```

#### Login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario",
    "password": "senha123"
  }'
```

#### Criar Produto (com token)

```bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_JWT" \
  -d '{
    "nome": "Notebook Dell",
    "descricao": "i7, 16GB RAM, 512GB SSD",
    "preco": 4500.00,
    "quantidade": 10
  }'
```

#### Listar Produtos

```bash
curl -X GET http://localhost:8080/produtos \
  -H "Authorization: Bearer SEU_TOKEN_JWT"
```

---

## 🔐 Autenticação JWT

Para consumir os endpoints protegidos:

1. **Registre um usuário** via `POST /auth/register`
2. **Faça login** via `POST /auth/login`
3. **Receba o token JWT** no corpo da resposta
4. **Inclua o token** no header das requisições:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Fluxo de Autenticação

```
Cliente → POST /auth/login → Servidor
         ← Token JWT ←

Cliente → GET /produtos (+ Token) → Servidor
         ← Lista de Produtos ←
```

---

## ⚙️ Configuração

### Variáveis de Ambiente

#### Docker (docker-compose.yml)

```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/meubanco
  SPRING_DATASOURCE_USERNAME: postgres
  SPRING_DATASOURCE_PASSWORD: F@53a6!lP9w0rd$2#
```

#### Local (application.properties)

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/meubanco
spring.datasource.username=postgres
spring.datasource.password=F@53a6!lP9w0rd$2#

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server
server.port=8080
```

### Executar Localmente (sem Docker)

#### Pré-requisitos

- Java 17+
- Maven 3.9+
- PostgreSQL 16+

#### Passos

```bash
# 1. Criar banco de dados
createdb meubanco

# 2. Configurar application.properties
# (ajustar credenciais do PostgreSQL)

# 3. Compilar e executar
mvn clean install
mvn spring-boot:run
```

---

## 🧪 Testando a API

Você pode utilizar:

- **Postman** - [Download](https://www.postman.com/)
- **Insomnia** - [Download](https://insomnia.rest/)
- **cURL** - Linha de comando
- **Thunder Client** - Extensão do VS Code

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Siga os passos:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

### Padrões de Código

- Seguir convenções do Spring Boot
- Documentar endpoints importantes
- Escrever testes para novas funcionalidades
- Manter o código limpo e legível

---

## 📄 Licença

Este projeto é de livre uso para fins educacionais e pode ser utilizado como base para novas aplicações Spring Boot.

---

**Desenvolvido como projeto de estudo** - Baseado na vídeo aula "API completa com Spring Boot" no YouTube

**Versão**: 1.0.0

**Última Atualização**: Novembro 2025

---
