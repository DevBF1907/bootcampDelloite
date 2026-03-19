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
* Frontend com HTML, CSS e JavaScript puro
* Bootstrap 5

---

# 🌿 Trajetória do Projeto — Branches

Cada desafio possui sua própria branch, documentando a evolução completa do projeto do zero até a interface visual com Bootstrap.

| Branch | Desafio | Descrição |
|---|---|---|
| `main` | Desafio 1 | CRUD com lógica de programação no terminal |
| `feature/desafio2` | Desafio 2 | Refatoração para Programação Orientada a Objetos |
| `feature/desafio3` | Desafio 3 | Migração para Spring Boot e API REST |
| `feature/testesUnitarios` | Desafio 4 | Testes unitários com JUnit 5 e Mockito |
| `feature/adicionandoSOLID` | Desafio 5 | Aplicação dos princípios SOLID (SRP e OCP) |
| `feature/TestesUnitariosSOLID` | Desafio extra | Testes unitários na aplicação com SOLID |
| `feature/desafioFront` | Desafio 6 | Interface web com Bootstrap integrada ao Spring Boot |

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

**🗑️ Deletar usuário**
```http
DELETE http://localhost:8080/usuarios/1
```

### 🛠️ Ferramentas disponíveis

**Swagger UI:**
```
http://localhost:8080/swagger-ui/index.html
```

**H2 Console:**
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

### ✅ Princípios aplicados

**S — Single Responsibility Principle (SRP)**
> Uma classe deve ter apenas uma responsabilidade.

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

Foi criada a interface `UsuarioValidation` como contrato. O `UsuarioService` recebe uma `List<UsuarioValidation>` e o Spring injeta todas as implementações com `@Component` automaticamente. Para adicionar uma nova validação, basta criar uma nova classe — **sem modificar o service**.

### O que foi feito

* Criação da interface `UsuarioValidation` como contrato das validações
* Extração de todas as validações do `UsuarioService` para classes independentes com `@Component`
* Adição dos campos `cpf` e `telefone` na entidade `Usuario`
* Implementação de `CpfValidation` e `TelefoneValidation` — desafio extra do case
* `UsuarioService` completamente limpo — só orquestra, não valida mais nada
* Substituição do `@Autowired` por injeção via construtor com `@RequiredArgsConstructor`
* Atualização dos testes unitários — **32 testes no total**

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

### 🧪 Cobertura de testes — 32 testes

| Método | Cenários testados |
|---|---|
| `criarUsuario` | sucesso, nome (vazio, nulo, curto, longo, especiais, duplicado), email (vazio, inválido, duplicado), senha (vazia, curta, longa), CPF (vazio, inválido), telefone (vazio, inválido) |
| `listarUsuarios` | lista com usuários, lista vazia |
| `buscarUsuarioPorId` | ID existente, ID inexistente |
| `atualizarUsuario` | sucesso, ID inexistente |
| `deletarUsuario` | sucesso, ID inexistente |

---

## 📌 Desafio 6 — Interface Web com Bootstrap

**Branch:** `feature/frontend`

Neste desafio o objetivo foi criar uma **interface web** para o sistema de usuários, integrando um frontend em HTML, CSS e JavaScript puro diretamente ao projeto Spring Boot, consumindo a API REST já implementada.

### O que foi feito

* Criação de interface web servida pelo próprio Spring Boot via pasta `static/`
* Uso do **Bootstrap 5** para estilização e componentes
* Consumo da API REST com **Fetch API** em JavaScript puro
* Separação dos arquivos em `index.html`, `style.css` e `app.js`
* Interface com **duas abas separadas** — uma para cadastro e outra para edição
* Melhorias no `UsuarioService` para suportar **atualização parcial** dos campos
* Correção das validações de duplicidade para ignorar o próprio usuário na edição
* Nova regra de negócio que impede atualização com a **mesma senha atual**

### Melhorias no backend

**Atualização parcial de campos** — ao editar um usuário, apenas os campos preenchidos são atualizados. Campos deixados em branco mantêm o valor atual:

```java
// Só atualiza o campo se vier preenchido
if (dto.nome() != null && !dto.nome().isBlank()) usuario.setNome(dto.nome());
if (dto.email() != null && !dto.email().isBlank()) usuario.setEmail(dto.email());
if (dto.senha() != null && !dto.senha().isBlank()) usuario.setSenha(dto.senha());
if (dto.cpf() != null && !dto.cpf().isBlank()) usuario.setCpf(dto.cpf());
if (dto.telefone() != null && !dto.telefone().isBlank()) usuario.setTelefone(dto.telefone());
```

**Validação de duplicidade ignorando o próprio usuário** — a interface `UsuarioValidation` ganhou um método `default` com `idIgnorar`, sobrescrito nas validações de duplicidade de nome e email:

```java
// Ignora o próprio usuário ao verificar duplicidade
default void validar(UsuarioRequestDTO dto, Long idIgnorar) {
    validar(dto);
}
```

**Bloqueio de senha igual** — impede que o usuário atualize a senha com o mesmo valor atual:

```java
if (dto.senha() != null && !dto.senha().isBlank() && dto.senha().equals(usuario.getSenha())) {
    throw new UsuarioException("A nova senha não pode ser igual à senha atual.");
}
```

**Novos métodos no `UsuarioRepository`** para busca por nome e email retornando `Optional`, necessários para a validação de duplicidade com `idIgnorar`:

```java
Optional<Usuario> findByEmailIgnoreCase(String email);
Optional<Usuario> findByNomeIgnoreCase(String nome);
```

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
│   │   │   ├── UsuarioValidation.java
│   │   │   ├── NomeValidation.java
│   │   │   ├── NomeDuplicadoValidation.java
│   │   │   ├── EmailValidation.java
│   │   │   ├── EmailDuplicadoValidation.java
│   │   │   ├── SenhaValidation.java
│   │   │   ├── CpfValidation.java
│   │   │   └── TelefoneValidation.java
│   │   └── Exception/
│   │       ├── UsuarioException.java
│   │       └── GlobalExceptionHandler.java
│   └── resources/
│       ├── static/
│       │   ├── index.html          ← estrutura HTML
│       │   ├── style.css           ← estilos customizados
│       │   └── app.js              ← lógica de consumo da API
│       └── application.properties
└── test/
    └── java/com/bootcampdelloite/desafiospringboot/
        └── Service/
            └── UsuarioServiceTest.java
```

---

### 🚀 Guia de uso do projeto

#### Pré-requisitos

- Java 17+
- Maven
- Navegador web

#### Como rodar

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/bootcampDelloite.git

# 2. Acesse a branch
git checkout feature/desafioFront

# 3. Rode o projeto
./mvnw spring-boot:run
```

Acesse a interface no navegador:
```
http://localhost:8080
```

---

### 🖥️ Funcionalidades da interface

**Aba Novo Usuário** — formulário para cadastrar um novo usuário com todos os campos: nome, email, senha, CPF e telefone.

**Aba Editar Usuário** — exibe uma mensagem orientando o usuário a clicar no ícone de lápis na tabela. Ao clicar, a aba abre automaticamente com os dados preenchidos. Todos os campos são opcionais — só os preenchidos serão atualizados.

**Tabela de usuários** — lista todos os usuários cadastrados com contador de registros e botão de atualizar. Cada linha tem botões de editar e deletar.

**Modal de confirmação** — ao clicar em deletar, um modal exibe o nome do usuário e pede confirmação antes de remover.

**Alertas** — mensagens de erro em vermelho com o retorno exato da API, e mensagens de sucesso em verde que somem automaticamente após 3 segundos.

---

### 📋 Regras de negócio da edição

| Situação | Comportamento |
|---|---|
| Campo preenchido | Valida e atualiza o valor |
| Campo deixado em branco | Mantém o valor atual do banco |
| Email já cadastrado por outro usuário | Retorna erro `400` |
| Nome já cadastrado por outro usuário | Retorna erro `400` |
| Senha igual à senha atual | Retorna erro `400` — `"A nova senha não pode ser igual à senha atual."` |

---

### 📨 Exemplos de requisição

**✅ Cadastrar usuário**
```http
POST http://localhost:8080/usuarios
Content-Type: application/json

{
    "nome": "Renato creator of Pix",
    "email": "renato@gmail.com",
    "senha": "pix12345",
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

**✏️ Atualizar apenas o email**
```http
PUT http://localhost:8080/usuarios/1
Content-Type: application/json

{
    "email": "renato.novo@gmail.com"
}
```

Resposta `200 OK`:
```json
{
    "id": 1,
    "nome": "Renato creator of Pix",
    "email": "renato.novo@gmail.com",
    "cpf": "123.456.789-09",
    "telefone": "11987654321"
}
```

---

**✏️ Atualizar apenas a senha**
```http
PUT http://localhost:8080/usuarios/1
Content-Type: application/json

{
    "senha": "novaSenha123"
}
```

---

**🗑️ Deletar usuário**
```http
DELETE http://localhost:8080/usuarios/1
```

---

### ⚠️ Testando as validações da edição


**Email já cadastrado por outro usuário:**
```json
{ "email": "carlos@gmail.com" }
```
> `400` — `"Esse email já está cadastrado."`

**Nome já cadastrado por outro usuário:**
```json
{ "nome": "Carlos Mendes" }
```
> `400` — `"Já existe um usuário com esse nome."`

---

### 🛠️ Ferramentas disponíveis

**Interface web:**
```
http://localhost:8080
```

**Swagger UI:**
```
http://localhost:8080/swagger-ui/index.html
```

**H2 Console:**
```
http://localhost:8080/h2-console
JDBC URL:  jdbc:h2:mem:desafio
Username:  sa
Password:  (deixar em branco)
```

---

✍️ Este repositório representa a trajetória completa do Bootcamp Deloitte — do CRUD mais simples em lógica de programação até uma API REST com arquitetura em camadas, testes unitários, princípios SOLID e interface web integrada.
