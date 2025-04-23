# 🔐 User Authentication API – Spring Boot

API REST construída com **Spring Boot** para gerenciamento e autenticação de usuários. Inclui criação de contas, login com geração de token JWT e acesso ao perfil autenticado.

## ⚙️ Tecnologias

- Java + Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA

---

## 📌 Endpoints da API

### ✅ Criar Usuário
`POST /api/users/create`

Cria um novo usuário no banco de dados.

**Corpo da requisição:**
```json
{
  "name": "João da Silva",
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Resposta:**
```json
{
  "id": 1,
  "name": "João da Silva",
  "email": "joao@email.com"
}
```

---

### 🔐 Login
`POST /api/users/login`

Autentica o usuário e retorna um token JWT.

**Corpo da requisição:**
```json
{
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Resposta:**
```json
{
  "token": "jwt.token.aqui"
}
```

---

### 👤 Obter Perfil
`GET /api/users/profile`

Retorna os dados do usuário atualmente autenticado.

**Cabeçalho necessário:**
```
Authorization: Bearer jwt.token.aqui
```

**Resposta:**
```json
{
  "id": 1,
  "name": "João da Silva",
  "email": "joao@email.com"
}
```

---

## 🚧 Requisitos

- Java 17+
- Spring Boot 3.x
- Banco de dados configurado (ex: PostgreSQL ou H2)
