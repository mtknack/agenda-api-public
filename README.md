<h1 align="center">🗂️ Agenda API</h1>

<p align="center">
  <strong>Backend do sistema de gerenciamento de tarefas</strong><br>
  Desenvolvido com Java + Spring Boot + PostgreSQL
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-red?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.1.0-green?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql&logoColor=white" />
</p>

---

## 📋 Sobre o Projeto

A **Agenda API** é o backend de um sistema de gerenciamento de tarefas. Permite registrar tarefas, classificá-las por importância e data, atualizá-las e excluí-las conforme a necessidade.

Este projeto faz parte de um conjunto de aplicações pessoais fullstack, com o frontend sendo desenvolvido em Angular (em repositório separado).

---

## 🛠️ Tecnologias Utilizadas

- ✅ Java 17
- ✅ Spring Boot
- ✅ PostgreSQL
- ✅ Maven
- ✅ Spring Data JPA
- ✅ Hibernate

---

## 📁 Estrutura do Projeto

```
agenda-api-public/
├── src/
│   └── main/
│       ├── java/com/mtknack/agenda/
│       │   ├── controller/
│       │   ├── model/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

---

## ⚙️ Como Executar Localmente

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/mtknack/agenda-api-public.git
cd agenda-api-public
```

### 2️⃣ Configure o PostgreSQL

Crie um banco de dados local com o nome que preferir, por exemplo:

```sql
CREATE DATABASE agenda_db;
```

### 3️⃣ Configure o arquivo `application.properties`

Edite o arquivo `src/main/resources/application.properties` com suas credenciais do banco:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/agenda_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### 4️⃣ Execute a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em:  
📍 `http://localhost:8080`

---

## 🔗 Principais Endpoints

| Método | Endpoint         | Descrição                          |
|--------|------------------|------------------------------------|
| GET    | `/tasks`         | Lista todas as tarefas             |
| POST   | `/tasks`         | Cria uma nova tarefa               |
| PUT    | `/tasks/{id}`    | Atualiza uma tarefa existente      |
| DELETE | `/tasks/{id}`    | Exclui uma tarefa                  |
| GET    | `/tasks?sort=...`| Ordena tarefas por data/importância |

---

## ✨ Funcionalidades

- ✅ Criação de tarefas com nome, descrição, importância e data
- ✅ Ordenação de tarefas por data ou importância
- ✅ Atualização e exclusão de tarefas
- ✅ Validações de dados no backend

---

## 💬 Contribuição

Contribuições são super bem-vindas!  
Abra uma [issue](https://github.com/mtknack/agenda-api-public/issues) ou envie um pull request com melhorias.  

---

## 🧾 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

<p align="center">
  Feito com 💻 por <a href="https://github.com/mtknack">mtknack</a>
</p>
