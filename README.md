# 🎵 Music API

API REST para gerenciamento de álbuns e artistas, construída com **Spring Boot** e documentada com **OpenAPI 3.0**.

## 🚀 Tecnologias
- Java 21+
- Spring Boot
- Spring Security (JWT)
- Bucket4j (Rate Limiting)
- MinIO (armazenamento de capas)
- JPA / Hibernate
- Lombok
- OpenAPI / Swagger UI

## 📦 Endpoints principais

### Álbuns
- `POST /api/v1/albums` → Cadastrar álbum  
  **Response:** `201 Created`

### Regionais
- `GET /api/v1/regionais` → Listar regionais ativas  
  **Response:** `200 OK`

- `POST /api/v1/regionais/sincronizar` → Sincronizar regionais externas  
  **Response:** `200 Sincronizado`

## 🔑 Autenticação
- JWT baseado em Spring Security.
- Endpoints públicos: `/auth/**`, `/actuator/**`, `/v3/api-docs/**`.
- Demais endpoints requerem token válido.

## ⚙️ Configuração
### application.properties
```properties
spring.application.name=musicapi

# MinIO
minio.url=http://localhost:9000
minio.access-key=admin
minio.secret-key=admin123
