# 🛒 API de Produtos com Spring Boot

Esta é uma API RESTful construída com **Spring Boot** e **PostgreSQL**, seguindo boas práticas de arquitetura, tratamento de exceções e autenticação com **JWT (JSON Web Token)**. O objetivo é permitir o gerenciamento de um catálogo de produtos de forma segura, eficiente e escalável.

---

## 🚀 Funcionalidades

- ✅ Cadastro, listagem, edição e remoção de produtos
- ✅ Autenticação de usuários com JWT
- ✅ Proteção de rotas com roles e tokens
- ✅ Tratamento global de exceções
- ✅ Integração com banco de dados PostgreSQL
- ✅ Documentação de endpoints com Swagger

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- JWT (JSON Web Token)
- Lombok
- Swagger (OpenAPI)

---

## 🧱 Estrutura do Projeto


com.example.api_completa_com_spring_boot ├── controller ├── model ├── repository ├── service ├── config └── exceptions

---

## 🔐 Autenticação com JWT

Para consumir os endpoints protegidos, é necessário:

1. Realizar login via `POST /auth/login`
2. Receber o token JWT no corpo da resposta
3. Incluir o token no header das requisições subsequentes:


---

## 📦 Endpoints principais

| Método | Rota               | Acesso      | Descrição                        |
|--------|--------------------|-------------|----------------------------------|
| GET    | `/produtos`        | Autenticado | Lista todos os produtos          |
| POST   | `/produtos`        | Autenticado | Cadastra um novo produto         |
| PUT    | `/produtos/{id}`   | Autenticado | Atualiza um produto existente    |
| DELETE | `/produtos/{id}`   | Autenticado | Remove um produto do sistema     |
| POST   | `/auth/login`      | Público     | Retorna token JWT                |

---

## 💾 Configuração do Banco de Dados

Arquivo: `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update



🧪 Testando a API
Você pode utilizar o Postman ou Insomnia para testar os endpoints.
Para facilitar, use o Swagger UI (caso habilitado).

🧑‍💻 Autor
Desenvolvido por mim — como parte de um estudo prático com base na vídeo aula “API completa com Spring Boot” no YouTube.

📄 Licença
Este projeto é de livre uso para fins educacionais e pode ser utilizado como base para novas aplicações Spring Boot. ✨
