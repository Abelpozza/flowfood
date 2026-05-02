# 🍔 FlowFood API

API RESTful de um sistema de delivery desenvolvida com foco em boas práticas de backend, arquitetura limpa e regras de negócio reais.

---

## 🚀 Tecnologias utilizadas

* Kotlin
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Hibernate
* Jakarta Validation

---

## 📌 Funcionalidades

### 🏪 Restaurantes

* Criar restaurante
* Listar restaurantes
* Buscar por ID

### 🍕 Produtos

* Criar produto vinculado a um restaurante
* Listar produtos
* Buscar produtos por restaurante
* Atualizar e deletar produto

### 📦 Pedidos

* Criar pedido com múltiplos produtos
* Cálculo automático do valor total
* Listar pedidos
* Buscar pedido por ID
* Atualizar status do pedido

---

## 🔄 Fluxo de Pedido (Regra de Negócio)

O sistema implementa controle de status com regras válidas:

```
CREATED → CONFIRMED → DELIVERED
CREATED → CANCELED
CONFIRMED → CANCELED
```

Transições inválidas são bloqueadas automaticamente.

---

## 🧠 Conceitos aplicados

* Separação em camadas (Controller, Service, Repository)
* DTOs para comunicação externa
* Tratamento global de exceções
* Validação de dados com annotations
* Relacionamentos JPA (OneToMany, ManyToMany)
* Regras de negócio no Service
* State Machine básica para pedidos

---

## 📂 Estrutura do projeto

```
com.flowfood
 ├── config
 ├── exception
 ├── restaurant
 │    ├── controller
 │    ├── dto
 │    ├── entity
 │    ├── repository
 │    └── service
 ├── product
 │    ├── controller
 │    ├── dto
 │    ├── entity
 │    ├── repository
 │    └── service
 ├── order
 │    ├── controller
 │    ├── dto
 │    ├── entity
 │    ├── repository
 │    └── service
```

---

## 🧪 Exemplos de requisições

### Criar restaurante

```http
POST /restaurants
```

```json
{
  "name": "Pizza Prime",
  "description": "Pizzaria artesanal",
  "deliveryFee": 7.5,
  "open": true
}
```

---

### Criar produto

```http
POST /products
```

```json
{
  "name": "Pizza Calabresa",
  "description": "Pizza grande",
  "price": 49.90,
  "restaurantId": 1
}
```

---

### Criar pedido

```http
POST /orders
```

```json
{
  "customerName": "Abel",
  "productIds": [1]
}
```

---

### Atualizar status do pedido

```http
PATCH /orders/{id}/status?status=CONFIRMED
```

---

## ⚠️ Observações

* O sistema valida a existência de restaurante e produtos antes de criar pedidos
* O valor total do pedido é calculado automaticamente
* Não é permitido alterar status de forma inválida

---

## 🎯 Objetivo do projeto

Este projeto foi desenvolvido com foco em:

* Evolução prática em backend
* Simulação de regras reais de negócio
* Construção de portfólio para oportunidades como Backend Developer

---

## 👨‍💻 Autor

Desenvolvido por Abel Pozza

---
