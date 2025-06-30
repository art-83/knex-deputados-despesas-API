# Arthur Nicolas - API de Deputados e Despesas

API desenvolvida com **Spring Boot** para importação, persistência e exposição de dados sobre **deputados federais** e suas **despesas parlamentares**.

A aplicação oferece integração com arquivos CSV e API HTTP externa, armazena os dados em um banco PostgreSQL, e expõe endpoints RESTful para consultas e relatórios.

---

## \>> Tecnologias Utilizadas

- Java 17
- Spring Boot (Spring Data JPA, SpringWeb, Lombok, PostgresSQL Driver)
- PostgreSQL
- Docker e Docker Compose
- Maven

---

## \>> Estrutura dos packages
br.knex.arthur_nicolas\
├── 🗀 configuration\
├── 🗀 controllers\
├── 🗀 dto\
├── 🗀 importer\
├── 🗀 mappers\
├── 🗀 models\
├── 🗀 repositories\
├── 🗀 services\
└── ©️ ArthurNicolasApplication\

---

## \>> Como executar a aplicação
### Requisitos:
1. JDK **17+**
2. IntelliJ (ou outra IDE de preferência)
3. PostgresSQL


- _**OPCIONAL:**_


1. pgAdmin4 (ou outra interface gráfica para gerenciar e visualizar o banco de dados)
2. Postman (ou outra ferramenta para fazer requisições HTTP)

### Passo a Passo:
1. Crie um banco de dados chamado "knex_db"
2. Esteja com a porta **8080** disponível, caso contrário, o Spring não vai conseguir se conectar com o banco de dados no **PostgresSQL**
3. Importe o projeto para sua IDE, e deixe o Maven baixar todas as dependências. Caso não reconheça, no **terminal da IDE**, digite: `mvn clean install`
4. Compile programa em `ArthurNicolasApplication.java`
5. Faça requisições usando Postman ou outa ferramenta.

## \>> Endpoints

**OBS.:** @RequestParam apenas em `/deputado/{id}`

| Rota                         | Verbo HTTP | `@PathVariable`/ `@Requestparam` | Descrição                                                   |
|------------------------------|------------|----------------------------------|-------------------------------------------------------------|
| `/deputado?uf={uf}`          | `GET`      | `uf`                             | Lista de deputados pelo UF                                  |
| `/relatorios/{id}/despesas`  | `GET`      | `id`                             | Soma de despesas pelo ID                                    |
| `/relatorios/total-despesas` | `GET`      | Sem parâmetros.                  | Soma de todas as despesas                                   |
| `/despesa/`                  | `GET`      | Sem parâmetros.                  | Lista de todas as despesas                                  |
| `/deputado/estado/{uf}`      | `GET`      | `uf`                             | Lista despesas por UF                                       |
| `/database/init`             | `Put`      | Sem parâmetros.                  | ResponseBody sinalizando que o banco de dados foi populado. |

Use o endereço `http://localhost:8080` para fazer as requisições.

## \>> Como funciona internamente?
### Visão Geral da Arquitetura

A aplicação segue uma arquitetura em **camadas**, organizada em pacotes com responsabilidades bem definidas.

### Lógica da Aplicação

| Camada              | Pacote                         | Função principal                                                                |
|---------------------|--------------------------------|---------------------------------------------------------------------------------|
| **Entrada (API)**   | `controllers`                  | Define os endpoints REST que recebem e respondem às requisições HTTP            |
| **Serviços**        | `services`                     | Contém a lógica de negócio e orquestra chamadas aos repositórios e importadores |
| **Repositórios**    | `repositories`                 | Interface com o banco de dados via Spring Data JPA + Queries personalizadas     |
| **Modelos**         | `models`                       | Representa as entidades persistidas no banco de dados                           |
| **DTOs**            | `dto`, `dto.response`          | Estruturas de dados transferidos entre camadas (input/output da API)            |
| **Mappers**         | `mappers`                      | Convertem entre Modelos e DTOs                                                  |
| **Importadores**    | `importer.csv`, `importer.http`| Lidam com ingestão de dados externos via CSV ou API HTTP                        |
| **Configuração**    | `configuration`                | Define configurações da aplicação                                               |
| **Main**            | `ArthurNicolasApplication.java`| Classe principal que inicializa o Spring Boot                                   |

---

### Fluxo de Funcionamento

1. **Importação de Dados**
    - `DatabaseController` chama métodos em `DespesaCsvImport` e `DeputadoHttpImport` para carregar os dados.
    - As despesas vêm de **CSV** e os deputados de uma **API HTTP**.

2. **Persistência**
    - Os dados importados são convertidos via `Mapper` e salvos nos repositórios `DeputadoRepository` e `DespesaRepository`.

3. **Consultas**
    - A API oferece endpoints para consultar deputados, despesas e gerar relatórios a partir das despesas.
    - A lógica de consulta no banco de dados está em `DeputadoQueryService` e `DespesaQueryService`.

4. **Retorno de Dados**
    - Os resultados são transformados em `DTOs` de resposta (`DeputadoResponseDTO`, `DespesaResponseDTO`) para filtrar o que vai ser exposto, e finalmente são retornados ao cliente.

---
## \>> Observações gerais sobre o desenvolvimento
Essa API RESTful foi a primeira API que fiz em toda minha vida, e o primeiro projeto usando algum Framework, e finalmente vi e entendi a utilidade deles, fazia tempo que queria fazer algo parecido mas pela rotina da universidade, sempre deixei como prioridade secundária, mas finalmente saiu do papel.\
\
Antes disso meu conhecimento era limitado em Estrutura de Dados e Algoritmos que eu praticava no Leet Code, cada passo foi lento pra construir esse projeto mas valeu muito a pena, aumentou muito minha visão de como são as coisas e tenho orgulho de saber o que cada linha desse código faz, logicamente uma aplicação real vai ser mais complexa que isso, mas estou 100% disposto para aprender mais, e acredito que estar inserido em projetos reais vai acelerar muito essa curva de aprendizado.
### Dificuldades que enfrentei no projeto por ordem de dificuldade:
1. **`Docker`**. Infelizmente não consegui fazer a containerização da minha aplicação, tentei fazer na tarde do ultimo dia do prazo mas no fim das contas eu não estava entendendo o que estava escrevendo no `Dockerfile` e o `docker-compose.yml`, decidi remover totalmente do projeto pois não queria só fazer funcionar, o prazo estava acabando e tive que tomar essa decisão.
1. Modelagem das `Models` para popular meu banco de dados.
2. Criar a Query manualmente usando JPQL para fazer o `ResponseDTO` para os Request HTTP (Não escrever em sí, mas sim conseguir descobrir essa solução)
3. Ler o `CSV` e fazer o Parsing para um `DTO` (Novamente a dificuldade foi descobrir que existia uma dependência só pra isso, tentei fazer manualmente mas seria 10x mais trabalhoso e confuso)
4. Entender a estrutura de camadas, analisar e aplicar o **princípio da responsabilidade única** (Foi a primeira vez fazendo um projeto com mais de 1 package)
