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

Durante o bootcamp, trabalhei com:

* Lógica de Programação
* Versionamento com Git e GitHub
* Boas práticas de código
* Metodologias Ágeis (Scrum)
* Programação Orientada a Objetos (POO)
* Java com Spring Boot
* API REST
* JPA e Hibernate
* Banco de dados H2 em memória
* Lombok
* DTO (Data Transfer Object)
* Tratamento de exceções com Spring
* Documentação de API com Swagger (SpringDoc OpenAPI)
* Testes Unitários com JUnit 5
* Mocks com Mockito
* Princípios SOLID — SRP e OCP

---

# 🌿 Trajetória do Projeto — Branches

Cada desafio possui sua própria branch, documentando a evolução completa do projeto do zero até a aplicação dos princípios SOLID.

| Branch | Desafio | Descrição |
|---|---|---|
| `main` | Desafio 1 | CRUD com lógica de programação no terminal |
| `feature/desafio2` | Desafio 2 | Refatoração para Programação Orientada a Objetos |
| `feature/desafio3` | Desafio 3 | Migração para Spring Boot e API REST |
| `feature/testesUnitarios` | Desafio 4 | Testes unitários com JUnit 5 e Mockito |
| `feature/adicionandoSOLID` | Desafio 5 | Aplicação dos princípios SOLID (SRP e OCP) |
| `feature/TestesUnitariosSOLID` | Desafio extra | Testes unitários na aplicação com SOLID |

---

# 🏆 Desafios Desenvolvidos

## 📌 Desafio 1 — CRUD com Lógica de Programação

**Branch:** `main`

No primeiro desafio desenvolvi um **CRUD simples de usuários** utilizando apenas **lógica de programação**, sem aplicar ainda conceitos de Programação Orientada a Objetos.

Durante este desafio utilizei estruturas básicas da linguagem, como:

* Estruturas condicionais (`if` / `else`)
* Estruturas de repetição (`while`)
* Controle de fluxo através de **menu interativo no terminal**
* Manipulação de dados para realizar operações de **criar, listar, atualizar e remover usuários**

O objetivo principal foi **praticar lógica de programação e controle de fluxo**, entendendo como estruturar um sistema simples utilizando apenas recursos fundamentais da linguagem.

---

## 📌 Desafio 2 — Refatoração para Programação Orientada a Objetos

**Branch:** `feature/desafio2`

Neste desafio, o objetivo foi **refatorar o código do desafio anterior**, transformando-o em uma aplicação utilizando os princípios de **Programação Orientada a Objetos (POO)**.

### O que foi feito

* Criação da **classe `Usuario`** para representar a entidade do sistema
* Criação da **classe `UsuarioService`** para centralizar as regras de negócio
* Implementação das operações de **CRUD** de usuários com POO
* Separação de responsabilidades entre as classes
* Implementação de **validações de dados** e regras para evitar duplicidade de email e nome
* Implementação de **exceção personalizada (`UsuarioException`)**
* Aplicação de **tratamento de exceções** para tornar o sistema mais robusto
* Organização do fluxo da aplicação através de um **menu interativo no `Main`**

---

## 📌 Desafio 3 — Migração para Spring Boot e API REST

**Branch:** `feature/desafio3`

Neste desafio o objetivo foi **migrar o projeto do Desafio 2** — que rodava no terminal — para uma **API REST** utilizando **Spring Boot**.

### O que foi feito

* Migração completa do projeto legado Java para **Spring Boot**
* Substituição do `Scanner` e menu no terminal por **endpoints REST**
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
│   └── UsuarioController.java      ← endpoints REST
├── Service/
│   └── UsuarioService.java         ← regras de negócio
├── Repository/
│   └── UsuarioRepository.java      ← acesso ao banco
├── Model/
│   └── Usuario.java                ← entidade JPA
├── Dto/
│   ├── UsuarioRequestDTO.java      ← dados de entrada
│   └── UsuarioResponseDTO.java     ← dados de saída (sem senha)
└── Exception/
    ├── UsuarioException.java       ← exceção customizada
    └── GlobalExceptionHandler.java ← tratamento global de erros
```

### Como rodar

```bash
git checkout feature/desafio3
./mvnw spring-boot:run
```


### 📋 Endpoints disponíveis

| Método | Endpoint | Descrição | Status de sucesso |
|---|---|---|---|
| `POST` | `/usuarios` | Cadastrar novo usuário | `201 Created` |
| `GET` | `/usuarios` | Listar todos os usuários | `200 OK` |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID | `200 OK` |
| `PUT` | `/usuarios/{id}` | Atualizar usuário | `200 OK` |
| `DELETE` | `/usuarios/{id}` | Deletar usuário | `200 OK` |

---

### 📨 Exemplos de requisição

**✅ Cadastrar usuário com sucesso**
```http
POST http://localhost:8080/usuarios
Content-Type: application/json

{
    "nome": "Renato creator of Pix",
    "email": "renato@gmail.com",
    "senha": "123456"
}
```

Resposta `201 Created`:
```json
{
    "id": 1,
    "nome": "Renato creator of Pix",
    "email": "renato@gmail.com"
}
```

---

**📋 Listar todos os usuários**
```http
GET http://localhost:8080/usuarios
```

Resposta `200 OK`:
```json
[
    {
        "id": 1,
        "nome": "Renato creator of Pix",
        "email": "renato@gmail.com"
    }
]
```

---

**🔍 Buscar usuário por ID**
```http
GET http://localhost:8080/usuarios/1
```

Resposta `200 OK`:
```json
{
    "id": 1,
    "nome": "Renato creator of Pix",
    "email": "renato@gmail.com"
}
```

---

**✏️ Atualizar usuário**
```http
PUT http://localhost:8080/usuarios/1
Content-Type: application/json

{
    "nome": "Renato creator of Pix Atualizado",
    "email": "renato@gmail.com",
    "senha": "123456"
}
```

Resposta `200 OK`:
```json
{
    "id": 1,
    "nome": "Renato creator of Pix Atualizado",
    "email": "renato@gmail.com"
}
```

---

**🗑️ Deletar usuário**
```http
DELETE http://localhost:8080/usuarios/1
```

Resposta `200 OK`:
```
Usuário deletado com sucesso.
```

---

### 🛠️ Ferramentas disponíveis

**Swagger UI** — interface visual para testar os endpoints:
```
http://localhost:8080/swagger-ui/index.html
```

**H2 Console** — visualizar os dados salvos no banco em memória:
```
http://localhost:8080/h2-console

JDBC URL:  jdbc:h2:mem:desafio
Username:  sa
Password:  (deixar em branco)
```

---

## 📌 Desafio 4 — Testes Unitários com JUnit 5 e Mockito

**Branch:** `feature/testesUnitarios`

Neste desafio o objetivo foi **implementar testes unitários** na camada de Service do projeto Spring Boot, garantindo que todas as regras de negócio funcionem corretamente e de forma isolada.

### O que foi feito

* Testes unitários cobrindo todos os métodos do `UsuarioService`
* Uso do **JUnit 5** como framework de testes
* Uso do **Mockito** para simular o `UsuarioRepository` sem depender de banco de dados
* Aplicação do padrão **Arrange / Act / Assert** para organizar os testes
* Verificação das mensagens de exceção com `assertEquals`
* Uso do `verify()` para garantir que métodos do repository foram ou não chamados
* Cobertura de **cenários de sucesso e de erro** para cada operação do CRUD

### Conceitos aplicados

* `@ExtendWith(MockitoExtension.class)` — habilita o Mockito sem subir o Spring
* `@Mock` — simula o repository, sem bater no banco
* `@DisplayName` — descreve cada teste com linguagem natural
* `when(...).thenReturn(...)` — define o comportamento do mock
* `assertThrows(...)` — verifica se a exceção correta foi lançada
* `assertEquals(...)` — verifica valores retornados
* `verify(...)` — confirma se métodos foram ou não chamados

### Como rodar os testes

```bash
git checkout feature/testesUnitarios
./mvnw test
```

Ou no IntelliJ: botão direito na pasta `test` → **Run All Tests**

---

## 📌 Desafio 5 — Aplicando princípios SOLID (SRP e OCP)

**Branch:** `feature/adicionandoSOLID`

Neste desafio o objetivo foi aplicar dois princípios do **SOLID** diretamente no projeto Spring Boot, com base no **case prático proposto** pelo bootcamp, tornando o código mais organizado, extensível e preparado para evolução.

---

### 📖 Contexto do Case Proposto

O case apresentou o seguinte problema — um `UsuarioService` com múltiplas responsabilidades:

```java
// ❌ Antes — service acumulando validação, persistência e envio de email
public class UsuarioService {
    public void criarUsuario(Usuario usuario) {
        validarEmail(usuario);
        validarNome(usuario);
        usuarioRepository.save(usuario);
        enviarEmailBoasVindas(usuario); // responsabilidade a mais!
    }
}
```

O desafio foi **separar responsabilidades (SRP)** e **tornar as validações extensíveis sem alterar o service (OCP)**.

---

### ✅ Princípios aplicados

**S — Single Responsibility Principle (SRP)**
> Uma classe deve ter apenas uma responsabilidade.

Cada classe passou a ter uma única responsabilidade:

| Classe | Responsabilidade |
|---|---|
| `UsuarioController` | Receber e responder requisições HTTP |
| `UsuarioService` | Orquestrar o fluxo da operação |
| `UsuarioRepository` | Acesso ao banco de dados |
| `NomeValidation` | Validar apenas o nome |
| `EmailValidation` | Validar apenas o email |
| `SenhaValidation` | Validar apenas a senha |
| `NomeDuplicadoValidation` | Verificar duplicidade de nome no banco |
| `EmailDuplicadoValidation` | Verificar duplicidade de email no banco |
| `CpfValidation` | Validar apenas o CPF |
| `TelefoneValidation` | Validar apenas o telefone |
| `GlobalExceptionHandler` | Tratar erros da API |

**O — Open/Closed Principle (OCP)**
> Classes devem estar abertas para extensão, mas fechadas para modificação.

Foi criada a interface `UsuarioValidation` como contrato. O `UsuarioService` recebe uma `List<UsuarioValidation>` e o Spring injeta todas as implementações com `@Component` automaticamente. Para adicionar uma nova validação, basta criar uma nova classe — **sem modificar o service**:

```java
// ✅ Nova validação sem alterar o UsuarioService
@Component
public class CpfValidation implements UsuarioValidation {
    @Override
    public void validar(UsuarioRequestDTO dto) {
        // lógica de validação do CPF
    }
}
```

---

### O que foi feito

* Criação da interface `UsuarioValidation` como contrato das validações
* Extração de todas as validações do `UsuarioService` para classes independentes com `@Component`
* Adição dos campos `cpf` e `telefone` na entidade `Usuario`
* Implementação de `CpfValidation` e `TelefoneValidation` — desafio extra do case
* `UsuarioService` completamente limpo — só orquestra, não valida mais nada
* Substituição do `@Autowired` por injeção via construtor com `@RequiredArgsConstructor`
* Atualização dos testes unitários para cobrir todas as novas validações — **32 testes no total**

---

### Estrutura final do projeto

```
src/
├── main/
│   ├── java/com/bootcampdelloite/desafiospringboot/
│   │   ├── Controller/
│   │   │   └── UsuarioController.java
│   │   ├── Service/
│   │   │   └── UsuarioService.java
│   │   ├── Repository/
│   │   │   └── UsuarioRepository.java
│   │   ├── Model/
│   │   │   └── Usuario.java
│   │   ├── Dto/
│   │   │   ├── UsuarioRequestDTO.java
│   │   │   └── UsuarioResponseDTO.java
│   │   ├── Validation/
│   │   │   ├── UsuarioValidation.java          ← interface
│   │   │   ├── NomeValidation.java
│   │   │   ├── NomeDuplicadoValidation.java
│   │   │   ├── EmailValidation.java
│   │   │   ├── EmailDuplicadoValidation.java
│   │   │   ├── SenhaValidation.java
│   │   │   ├── CpfValidation.java              ← desafio extra
│   │   │   └── TelefoneValidation.java         ← desafio extra
│   │   └── Exception/
│   │       ├── UsuarioException.java
│   │       └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/bootcampdelloite/desafiospringboot/
        └── Service/
            └── UsuarioServiceTest.java         ← 32 testes
```

---

### 🚀 Guia de uso do projeto

#### Pré-requisitos

- Java 17+
- Maven
- Postman, Insomnia ou similar para testar os endpoints

#### Como rodar

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/bootcampDelloite.git

# 2. Acesse a branch
git checkout feature/adicionandoSOLID

# 3. Rode o projeto
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

#### Como rodar os testes

```bash
./mvnw test
```

Ou no IntelliJ: botão direito na pasta `test` → **Run All Tests**

---

### 🛠️ Ferramentas disponíveis

**Swagger UI** — interface visual para testar os endpoints sem precisar do Postman:
```
http://localhost:8080/swagger-ui/index.html
```

**H2 Console** — visualizar os dados salvos no banco em memória:
```
http://localhost:8080/h2-console

JDBC URL:  jdbc:h2:mem:desafio
Username:  sa
Password:  (deixar em branco)
```

---

### 📋 Endpoints disponíveis

| Método | Endpoint | Descrição | Status de sucesso |
|---|---|---|---|
| `POST` | `/usuarios` | Cadastrar novo usuário | `201 Created` |
| `GET` | `/usuarios` | Listar todos os usuários | `200 OK` |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID | `200 OK` |
| `PUT` | `/usuarios/{id}` | Atualizar usuário | `200 OK` |
| `DELETE` | `/usuarios/{id}` | Deletar usuário | `200 OK` |

---

### 📨 Exemplos de requisição

**✅ Cadastrar usuário com sucesso**
```http
POST http://localhost:8080/usuarios
Content-Type: application/json

{
    "nome": "Renato creator of Pix",
    "email": "renato@gmail.com",
    "senha": "123456",
    "cpf": "123.456.789-09",
    "telefone": "11987654321"
}
```

Resposta `201 Created`:
```json
{
    "id": 1,
    "nome": "Renato creator of Pix",
    "email": "renato@gmail.com",
    "cpf": "123.456.789-09",
    "telefone": "11987654321"
}
```

---

**📋 Listar todos os usuários**
```http
GET http://localhost:8080/usuarios
```

Resposta `200 OK`:
```json
[
    {
        "id": 1,
        "nome": "Renato creator of Pix",
        "email": "renato@gmail.com",
        "cpf": "123.456.789-09",
        "telefone": "11987654321"
    }
]
```

---

**🔍 Buscar usuário por ID**
```http
GET http://localhost:8080/usuarios/1
```

Resposta `200 OK`:
```json
{
    "id": 1,
    "nome": "Renato creator of Pix",
    "email": "renato@gmail.com",
    "cpf": "123.456.789-09",
    "telefone": "11987654321"
}
```

---

**✏️ Atualizar usuário**
```http
PUT http://localhost:8080/usuarios/1
Content-Type: application/json

{
    "nome": "Renato creator of Pix Atualizado",
    "email": "renato@gmail.com",
    "senha": "123456",
    "cpf": "123.456.789-09",
    "telefone": "11987654321"
}
```

Resposta `200 OK`:
```json
{
    "id": 1,
    "nome": "Renato creator of Pix Atualizado",
    "email": "renato@gmail.com",
    "cpf": "123.456.789-09",
    "telefone": "11987654321"
}
```

---

**🗑️ Deletar usuário**
```http
DELETE http://localhost:8080/usuarios/1
```

Resposta `200 OK`:
```
Usuário deletado com sucesso.
```

---

### ⚠️ Testando as validações

Exemplos de requisições que retornam erro `400 Bad Request`, úteis para validar o comportamento da API.

**Nome vazio:**
```json
{ "nome": "", "email": "renato@gmail.com", "senha": "123456", "cpf": "12345678909", "telefone": "11987654321" }
```
> `400` — `"Nome não pode ser vazio."`

**Nome com menos de 3 caracteres:**
```json
{ "nome": "Ab", "email": "renato@gmail.com", "senha": "123456", "cpf": "12345678909", "telefone": "11987654321" }
```
> `400` — `"Nome deve ter no mínimo 3 caracteres."`

**Nome com mais de 100 caracteres:**
```json
{ "nome": "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "email": "renato@gmail.com", "senha": "123456", "cpf": "12345678909", "telefone": "11987654321" }
```
> `400` — `"Nome deve ter no máximo 100 caracteres."`

**Nome com números ou caracteres especiais:**
```json
{ "nome": "Renat0@", "email": "renato@gmail.com", "senha": "123456", "cpf": "12345678909", "telefone": "11987654321" }
```
> `400` — `"Nome não pode conter números ou caracteres especiais."`

**Email inválido (não é @gmail.com):**
```json
{ "nome": "Renato creator of Pix", "email": "renato@hotmail.com", "senha": "123456", "cpf": "12345678909", "telefone": "11987654321" }
```
> `400` — `"Email inválido. Deve conter '@gmail.com'."`

**Senha com menos de 6 caracteres:**
```json
{ "nome": "Renato creator of Pix", "email": "renato@gmail.com", "senha": "123", "cpf": "12345678909", "telefone": "11987654321" }
```
> `400` — `"Senha deve ter no mínimo 6 caracteres."`

**Senha com mais de 50 caracteres:**
```json
{ "nome": "Renato creator of Pix", "email": "renato@gmail.com", "senha": "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "cpf": "12345678909", "telefone": "11987654321" }
```
> `400` — `"Senha deve ter no máximo 50 caracteres."`

**CPF vazio:**
```json
{ "nome": "Renato creator of Pix", "email": "renato@gmail.com", "senha": "123456", "cpf": "", "telefone": "11987654321" }
```
> `400` — `"CPF não pode ser vazio."`

**CPF com menos de 11 dígitos:**
```json
{ "nome": "Renato creator of Pix", "email": "renato@gmail.com", "senha": "123456", "cpf": "1234567", "telefone": "11987654321" }
```
> `400` — `"CPF deve conter 11 dígitos."`

**Telefone vazio:**
```json
{ "nome": "Renato creator of Pix", "email": "renato@gmail.com", "senha": "123456", "cpf": "12345678909", "telefone": "" }
```
> `400` — `"Telefone não pode ser vazio."`

**Telefone com menos de 10 dígitos:**
```json
{ "nome": "Renato creator of Pix", "email": "renato@gmail.com", "senha": "123456", "cpf": "12345678909", "telefone": "123456" }
```
> `400` — `"Telefone deve conter 10 ou 11 dígitos."`

**ID não encontrado:**
```http
GET http://localhost:8080/usuarios/99
```
> `404` — `"Usuário com ID 99 não encontrado."`

---

### 🧪 Cobertura de testes — 32 testes

| Método | Cenários testados |
|---|---|
| `criarUsuario` | sucesso, nome (vazio, nulo, curto, longo, especiais, duplicado), email (vazio, inválido, duplicado), senha (vazia, curta, longa), CPF (vazio, inválido), telefone (vazio, inválido) |
| `listarUsuarios` | lista com usuários, lista vazia |
| `buscarUsuarioPorId` | ID existente, ID inexistente |
| `atualizarUsuario` | sucesso, ID inexistente |
| `deletarUsuario` | sucesso, ID inexistente |

---

✍️ Este repositório representa a trajetória completa do Bootcamp Deloitte — do CRUD mais simples em lógica de programação até uma API REST com arquitetura em camadas, testes unitários e princípios SOLID aplicados.
