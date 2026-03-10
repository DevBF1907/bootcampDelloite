# 🚀 Bootcamp Deloitte - Evolução Técnica

## 👨‍💻 Sobre mim

Olá! Me chamo Brenno, sou estudante de Análise e Desenvolvimento de Sistemas e estou participando do Bootcamp da Deloitte com foco em evolução técnica, boas práticas e preparação para atuar como Analista Júnior.

Este repositório foi criado com o objetivo de documentar minha evolução durante o programa, registrando aprendizados, desafios, projetos e reflexões ao longo da jornada.

---

# 🎯 Objetivo deste Repositório

* Documentar minha evolução técnica
* Aplicar boas práticas de desenvolvimento
* Demonstrar organização e comprometimento
* Consolidar conhecimentos adquiridos no bootcamp
* Criar um histórico real do meu progresso

---

# 🧠 Tecnologias e Conceitos Estudados

Durante o bootcamp, estou trabalhando com:

* Lógica de Programação
* Versionamento com Git e GitHub
* Boas práticas de código
* Metodologias Ágeis (Scrum)
* Programação Orientada a Objetos (POO)
* Java com Spring Boot
* API REST
* JPA e Hibernate
* Banco de dados com H2
* Lombok
* DTO (Data Transfer Object)
* Tratamento de exceções com Spring
* Documentação de API com Swagger

*(Esta seção será atualizada conforme avanço no programa.)*

---

# 🏆 Desafios Desenvolvidos

## 📌 Desafio 1 — CRUD com Lógica de Programação

No primeiro desafio desenvolvi um **CRUD simples de usuários** utilizando apenas **lógica de programação**, sem aplicar ainda conceitos de Programação Orientada a Objetos.

Durante este desafio utilizei estruturas básicas da linguagem, como:

* Estruturas condicionais (`if` / `else`)
* Estruturas de repetição (`while`)
* Controle de fluxo através de **menu interativo no terminal**
* Manipulação de dados para realizar operações de **criar, listar, atualizar e remover usuários**

O objetivo principal foi **praticar lógica de programação e controle de fluxo**, entendendo como estruturar um sistema simples utilizando apenas recursos fundamentais da linguagem.

---

## 📌 Desafio 2 — Refatoração para Programação Orientada a Objetos

Neste desafio, o objetivo foi **refatorar o código do desafio anterior**, transformando-o em uma aplicação utilizando os princípios de **Programação Orientada a Objetos (POO)**.

Durante o desenvolvimento realizei as seguintes melhorias:

* Refatoração do código para aplicar **Programação Orientada a Objetos**
* Criação da **classe `Usuario`** para representar a entidade do sistema
* Criação da **classe `UsuarioService`** para centralizar as regras de negócio
* Implementação das operações de **CRUD (Create, Read, Update e Delete)** de usuários
* Separação de responsabilidades entre as classes
* Implementação de **validações de dados**, como verificação de email obrigatório
* Criação de regras de negócio para evitar **duplicidade de email e nome**
* Implementação de **exceção personalizada (`UsuarioException`)**
* Aplicação de **tratamento de exceções** para tornar o sistema mais robusto
* Organização do fluxo da aplicação através de um **menu interativo no `Main`**

Este desafio foi importante para consolidar conceitos de **POO, organização de código e aplicação de regras de negócio**, aproximando o desenvolvimento de práticas utilizadas em projetos reais.

---

## 📌 Desafio 3 — Migração para Spring Boot e API REST

Neste desafio o objetivo foi **migrar o projeto do Desafio 2** — que rodava no terminal — para uma **API REST** utilizando **Spring Boot**, aplicando conceitos e práticas utilizados no mercado de trabalho.

### O que foi feito

* Migração completa do projeto legado Java para **Spring Boot**
* Substituição do `Scanner` e menu no terminal por **endpoints REST**
* Criação de uma **API REST** com as operações de CRUD de usuários
* Aplicação do padrão de arquitetura em camadas (**Controller → Service → Repository**)
* Mapeamento da entidade `Usuario` com **JPA e Hibernate**
* Substituição do `ArrayList` por um **banco de dados H2** em memória
* Implementação do padrão **DTO** com `record` do Java para entrada e saída de dados
* Uso do **Lombok** para reduzir código boilerplate na entidade
* Tratamento global de exceções com **`@RestControllerAdvice`**
* Documentação da API com **Swagger (SpringDoc OpenAPI)**

### Estrutura do projeto

```
src/main/java/com/bootcampdelloite/desafiospringboot/
├── Controller/
│   └── UsuarioController.java     ← endpoints REST
├── Service/
│   └── UsuarioService.java        ← regras de negócio
├── Repository/
│   └── UsuarioRepository.java     ← acesso ao banco
├── Model/
│   └── Usuario.java               ← entidade JPA
├── Dto/
│   ├── UsuarioRequestDTO.java     ← dados de entrada
│   └── UsuarioResponseDTO.java    ← dados de saída (sem senha)
└── Exception/
    ├── UsuarioException.java      ← exceção customizada
    └── GlobalExceptionHandler.java ← tratamento global de erros
```

### Como rodar o projeto

**Pré-requisitos:**
- Java 17+
- Maven

**Passos:**

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/bootcampDelloite.git

# 2. Entre na pasta do desafio
cd desafio3

# 3. Rode o projeto
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

---

### 📋 Endpoints disponíveis

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/usuarios` | Cadastrar novo usuário |
| `GET` | `/usuarios` | Listar todos os usuários |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID |
| `PUT` | `/usuarios/{id}` | Atualizar nome do usuário |
| `DELETE` | `/usuarios/{id}` | Deletar usuário |

### Exemplos de requisição

**Cadastrar usuário**
```http
POST http://localhost:8080/usuarios
Content-Type: application/json

{
    "nome": "Brenno Felipe",
    "email": "brenno@gmail.com",
    "senha": "123456"
}
```

**Resposta:**
```json
{
    "id": 1,
    "nome": "Brenno Felipe",
    "email": "brenno@gmail.com"
}
```

**Atualizar usuário**
```http
PUT http://localhost:8080/usuarios/1
Content-Type: application/json

{
    "nome": "Brenno Atualizado",
    "email": "brenno@gmail.com",
    "senha": "123456"
}
```

**Deletar usuário**
```http
DELETE http://localhost:8080/usuarios/1
```

---

### 🛠️ Ferramentas disponíveis

**Swagger UI** — interface visual para testar os endpoints:
```
http://localhost:8080/swagger-ui/index.html
```

**H2 Console** — visualizar o banco de dados em memória:
```
http://localhost:8080/h2-console

JDBC URL:  jdbc:h2:mem:desafio1
Username:  sa
Password:  (deixar em branco)
```

---

✍️ Este repositório continuará sendo atualizado conforme avanço nos desafios e aprendizados dentro do Bootcamp.
