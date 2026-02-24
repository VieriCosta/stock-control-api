# 📦 Stock Control System

API RESTful desenvolvida em Spring Boot para controle de produtos, matérias-primas e cálculo de plano de produção com priorização por maior valor.

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security (estrutura preparada para JWT)
- PostgreSQL (Docker)
- Swagger / OpenAPI 3
- Maven
- JaCoCo (Code Coverage)
- Docker

---

## Arquitetura

O projeto foi desenvolvido seguindo separação de responsabilidades:

* com.vieri.stockcontrol
* ├── controller
* ├── service
* ├── repository
* ├── domain/entity
* ├── dto
* └── config


- **Controller** → Camada de entrada HTTP
- **Service** → Regras de negócio
- **Repository** → Persistência com JPA
- **DTO** → Objetos de transferência
- **Config** → Configurações globais

---

## Funcionalidades Implementadas

### CRUD de Produtos
- Criar produto
- Listar produtos
- Buscar por ID
- Atualizar
- Deletar

### CRUD de Matérias-Primas
- Criar matéria-prima
- Listar
- Buscar por ID
- Atualizar
- Deletar

### Associação Produto ↔ Matéria-Prima
- Definir quantidade necessária de insumo por produto

### Cálculo de Plano de Produção
- Simula produção com base no estoque disponível
- Prioriza produtos de maior valor
- Retorna quantidade produzível
- Retorna receita total estimada
- Não altera o estoque real

---

## Estratégia de Produção

O cálculo utiliza estratégia gulosa (Greedy Algorithm):

1. Ordena produtos por maior preço
2. Calcula quantidade máxima possível por insumo
3. Usa o menor valor entre eles
4. Atualiza estoque virtual
5. Calcula receita total

Fórmula utilizada:
maxUnits = stockQuantity / requiredQuantity


---

## Executando com Docker

### Subir banco

docker compose up -d
./mvnw spring-boot:run

### Acessando Swagger
Após iniciar a aplicação:

http://localhost:8080/swagger-ui/index.html

### Banco de Dados
 
PostgreSQL rodando via Docker:

Host: localhost
Porta: 5432
Database: stockdb
User: postgres
Password: postgres

---

### Segurança

Atualmente liberada para desenvolvimento.

Estrutura preparada para implementação futura de:

* JWT
* Controle por Roles (ADMIN / USER)

---

### Autor

Vieri Costa de Oliveira






