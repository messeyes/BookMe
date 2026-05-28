
# 📚 BookMe — Sistema de Gerenciamento de Biblioteca

O **BookMe** é uma aplicação web completa para gerenciamento de bibliotecas, desenvolvida como projeto acadêmico para colocar em prática conceitos de banco de dados, desenvolvimento backend e integração com frontend. O sistema cobre desde o cadastro de livros e usuários até o controle de empréstimos, reservas e multas — tudo exposto via **API REST**.

---

## ✨ O que o sistema faz

- Cadastro e controle do acervo de livros
- Gerenciamento de usuários
- Empréstimos e devoluções com atualização automática de estoque
- Reservas de livros indisponíveis
- Registro e controle de multas por atraso
- Restrição de empréstimo para usuários com multas pendentes

---

## 🛠️ Tecnologias utilizadas

| Camada | Tecnologias |
|---|---|
| Backend | Java, Spring Boot, Spring Data JPA, Hibernate |
| Banco de Dados | PostgreSQL |
| Frontend | HTML, CSS, JavaScript |
| Build | Gradle |
| Testes | Postman, Cypress |
| Versionamento | Git, GitHub |

---

## 📐 Arquitetura

O projeto segue o padrão de camadas:

```
Controller → Service → Repository → Banco de Dados
```

---

## 🚀 Como rodar o projeto

**Pré-requisitos:** Java 11+, PostgreSQL e Gradle instalados.

1. Clone o repositório:
```bash
git clone https://github.com/messeyes/BookMe.git
cd BookMe
```

2. Crie o banco de dados:
```sql
CREATE DATABASE bookme;
```

3. Configure o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookme
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. Suba a aplicação:
```bash
./gradlew bootRun
```

A API estará disponível em `http://localhost:8080`.

---
## 🧠 Sobre o desenvolvimento

Este projeto foi desenvolvido em equipe ao longo de **4 semanas**, com foco no backend. A escolha do Spring Boot, PostgreSQL e demais tecnologias foi intencional — eu queria aprender na prática, não só na teoria.

O maior desafio foi justamente o Spring Boot, que era uma tecnologia nova pra mim. Durante o desenvolvimento, mergulhei de cabeça em conceitos que antes eram só nomes: **annotations**, **injeção de dependência**, tratamento de **exceptions**, uso do **Optional** para evitar NullPointerExceptions e como isso tudo se conecta numa arquitetura em camadas.

Outro aprendizado importante foi o uso do **Postman** para testar os endpoints e entender na prática o que cada **código de status HTTP** significa — o que é um 200, quando lançar um 404, quando usar 400 ou 422. Isso mudou bastante a forma de pensar a API.

No banco de dados, foi onde conceitos acadêmicos viraram código de verdade: relacionamentos entre tabelas, chaves estrangeiras e como o JPA traduz tudo isso em Java.

No geral, foi um projeto que exigiu bastante, mas que consolidou muita coisa que só faz sentido quando você coloca a mão no código.

---

