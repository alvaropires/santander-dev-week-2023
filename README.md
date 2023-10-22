# Santander Dev Week 2023

Java RESTful API criada para projeto final do Bootcamp DIO JAVA SANTANDER

![Captura de tela de 2023-10-22 11-03-58](https://github.com/alvaropires/santander-dev-week-2023/assets/94912998/1c074b0e-a983-483a-9934-fe4d7cea6986)


## Principais tecnologias utilizadas

- [Intellij IDEA](https://www.jetbrains.com/pt-br/idea/)
- [PostgresSQL](https://www.postgresql.org/)
- [H2](https://www.h2database.com/)
- [Java 17](https://www.java.com/pt-BR/)
- [Spring Boot 3](https://spring.io/)
- [OpenAPI](https://www.openapis.org/)
- [RailWay](https://railway.app/)


## Funcionalidades

:heavy_check_mark: CRUD completo dos Usuários

✔️: Swagger para documentação, testes e consumo da API.

## SQL / Banco de Dados

Para este projeto, foram utilizados dois perfis de execução da aplicação. Sendo no perfil de Desenvolvimento utilizado um Banco de Dados [H2](https://www.h2database.com/) em memória e no perfil de Produção utilizado um banco de dados Postgres na nuvem a partir do [RailWay](https://railway.app/).



## Diagrama de classes (Domínio da API)

```mermaid
classDiagram
  class User {
    -String name
    -Account account
    -Feature[ ] features
    -Card card
    -News[ ] news
  }

  class Account {
    -String number
    -String agency
    -Number balance
    -Number limit
  }

  class Feature {
    -String icon
    -String description
  }

  class Card {
    -String number
    -Number limit
  }

  class News {
    -String icon
    -String description
  }

  User "1" *-- "1" Account
  User "1" *-- "N" Feature
  User "1" *-- "1" Card
  User "1" *-- "N" News
```

## [Link do Figma](https://www.figma.com/file/0ZsjwjsYlYd3timxqMWlbj/SANTANDER---Projeto-Web%2FMobile?type=design&node-id=1421-432&mode=design)

O Figma foi utilizado para a abstração do domínio desta API, sendo útil na análise e projeto da solução.

## Como rodar a aplicação

O link da API em produção está disponível abaixo:

[https://sdw-2023-prd.up.railway.app/swagger-ui.html](https://santander-dev-week-2023-production-dd7f.up.railway.app/swagger-ui/index.html)


Esta API ficará disponível no Railway por um período de tempo limitado, mas este é um código-fonte aberto. 
Portanto, sintam-se à vontade para cloná-lo, modificá-lo (já que é um bom projeto base para novos projetos) e executar localmente ou onde achar mais interessante!

Caso tenha interesse, você também pode estar acessando o projeto original divulgado no GitHub da DIO pelo Professor [Falvo Jr](https://github.com/falvojr) clicando [Aqui](https://github.com/digitalinnovationone/santander-dev-week-2023-api).

## Desenvolvedor

[<img src="https://avatars.githubusercontent.com/u/94912998?s=96&v=4" width=115><br><sub>Alvaro Pires Santos</sub>](https://github.com/alvaropires)

