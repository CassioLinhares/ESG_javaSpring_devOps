# Spring Backend ESG

API REST em Java com Spring Boot para gerenciamento de empresas, setores, equipamentos, consumos de energia, alertas e usuarios, com autenticacao JWT e controle de acesso por perfil.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- Bean Validation
- Flyway
- Oracle Database
- Lombok
- JWT com `java-jwt`

## Banco De Dados

O projeto usa Flyway para versionamento do banco. As migrations ficam em:

```text
src/main/resources/db/migration
```

As tabelas versionadas são:

- `tbl_usuario`
- `tbl_empresa`
- `tbl_setor`
- `tbl_equipamento`
- `tbl_consumo_energia`
- `tbl_alerta`

Atualmente o banco foi zerado e mantem apenas a tabela de usuários com três usuários iniciais:

| Perfil | Email | Senha |
| --- | --- | --- |
| Admin | `admin@example.com` | `123456` |
| Usuario | `user@example.com` | `123456` |
| Operador | `operador@example.com` | `123456` |

## Configuração

As configurações principais ficam em:

```text
src/main/resources/application.properties
```

Confira os dados de conexão do Oracle antes de executar:

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=RM565388
spring.datasource.password=fiap26
```

## Como Executar

No terminal, na raiz do projeto:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
.\mvnw.cmd spring-boot:run
```

A API sobe por padrao em:

```text
http://localhost:8080
```

## Autenticação

Para acessar as rotas protegidas, faça login em:

```http
POST /auth/login
```

Exemplo:

```json
{
  "email": "admin@example.com",
  "senha": "123456"
}
```

A resposta retorna um token JWT:

```json
{
  "token": "..."
}
```

Use esse token nas rotas protegidas:

```http
Authorization: Bearer SEU_TOKEN
```

## Regras De Acesso

- `POST /auth/login`: publico
- `POST /auth/register`: publico
- `DELETE /api/**`: somente `ADMIN`
- `POST /api/**`: somente `ADMIN`
- `PUT /api/**`: somente `ADMIN`
- `POST /api/consumos-energia`: `ADMIN` ou `OPERADOR`
- `POST /api/alertas`: `ADMIN` ou `OPERADOR`
- Demais rotas `/api/**`: usuario autenticado

## Perfis De Acesso

| Perfil | Descricao |
| --- | --- |
| `ADMIN` | Acesso total: GET, POST, PUT e DELETE em todos os recursos |
| `OPERADOR` | Pode registrar consumos de energia e alertas |
| `USER` | Acesso somente leitura nos recursos da API |

## Fluxo Sugerido Para Teste

Como o banco foi zerado e possui apenas usuarios, cadastre os dados nesta ordem:

1. Criar empresa
2. Criar setor vinculado a empresa
3. Criar equipamento vinculado ao setor
4. Criar consumo de energia vinculado ao equipamento
5. Criar alerta vinculado ao setor

## Principais Rotas

### Auth

```http
POST /auth/login
POST /auth/register
```

### Usuarios

```http
GET /api/usuarios
GET /api/usuarios/{id}
GET /api/usuarios/?nome=Admin
POST /api/usuarios
PUT /api/usuarios
DELETE /api/usuarios/{id}
```

### Empresas

```http
GET /api/empresas
GET /api/empresas/{id}
GET /api/empresas/?name=Empresa ESG
POST /api/empresas
PUT /api/empresas
DELETE /api/empresas/{id}
```

### Setores

```http
GET /api/setores
GET /api/setores/{id}
GET /api/setores/?nome=Producao
POST /api/setores
PUT /api/setores
DELETE /api/setores/{id}
```

### Equipamentos

```http
GET /api/equipamentos
GET /api/equipamentos/{id}
GET /api/equipamentos/?nome=Motor
GET /api/equipamentos/?setorId=1
POST /api/equipamentos
PUT /api/equipamentos
DELETE /api/equipamentos/{id}
```

### Consumos De Energia

```http
GET /api/consumos-energia
GET /api/consumos-energia/{id}
GET /api/consumos-energia?equipamentoId=1
GET /api/consumos-energia/soma?setorId=1&inicio=2026-05-01T00:00:00&fim=2026-05-31T23:59:59
POST /api/consumos-energia
PUT /api/consumos-energia
DELETE /api/consumos-energia/{id}
```

Exemplo de cadastro:

```json
{
  "consumoKwh": 120.5,
  "equipamento": {
    "id": 1
  }
}
```

### Alertas

```http
GET /api/alertas
GET /api/alertas/{id}
GET /api/alertas/?setorId=1
POST /api/alertas
PUT /api/alertas
DELETE /api/alertas/{id}
```

Exemplo de cadastro:

```json
{
  "mensagem": "Equipamento consumindo alem da conta",
  "setor": {
    "id": 1
  }
}
```

## Lembre de conferir os dados:

1. Confirme a variavel `base_url` como `http://localhost:8080` no Insomnia.
2. Faca login em `/auth/login`.
3. Copie o token retornado.
4. Configure manualmente o Bearer Token nas requisicoes protegidas.

## Validação

Para compilar o projeto:

```bash
.\mvnw.cmd -DskipTests compile
```

Para executar os testes:

```bash
.\mvnw.cmd test
```

## Docker

A imagem é gerada em duas etapas pelo `Dockerfile`: a primeira compila o projeto com o Maven Wrapper e a segunda executa apenas o `.jar` em uma JRE 21 com usuário sem privilégios.

```bash
docker build -t spring-backend-esg .
```

## Docker Compose

O `docker-compose.yml` sobe a aplicação junto com um Oracle XE 21:

| Recurso | Descrição |
| --- | --- |
| `oracle` | Banco Oracle XE com o usuário da aplicação criado no PDB `XEPDB1` |
| `app` | API Spring Boot, iniciada somente após o banco ficar saudável |
| `oracle-data` | Volume nomeado com os dados do banco |
| `esg-network` | Rede bridge usada na comunicação entre os serviços |

As variáveis de ambiente ficam em um arquivo `.env` (modelo em `.env.example`):

| Variável | Uso | Padrão |
| --- | --- | --- |
| `APP_IMAGE` | Imagem da aplicação | `spring-backend-esg:latest` |
| `APP_PORT` | Porta exposta no host | `8080` |
| `ORACLE_PASSWORD` | Senha do `SYSTEM` do Oracle | `oracle` |
| `DB_USER` | Usuário da aplicação no banco | `esg` |
| `DB_PASSWORD` | Senha do usuário da aplicação | `esg123` |
| `JWT_SECRET` | Chave de assinatura do JWT | `troque-esta-chave` |

```bash
cp .env.example .env
docker compose up -d --build
docker compose ps
docker compose down
```

Para apagar também os dados do banco:

```bash
docker compose down -v
```

## CI/CD

O pipeline fica em `.github/workflows/ci-cd.yml` e roda no GitHub Actions a cada push e pull request para a `main`, ou manualmente pela aba Actions.

| Job | O que faz |
| --- | --- |
| `Build` | Compila e empacota a aplicação com `./mvnw package` e publica o `.jar` como artefato |
| `Testes` | Sobe um Oracle XE como service container e executa `./mvnw test` com as migrations do Flyway |
| `Imagem Docker` | Gera a imagem pelo `Dockerfile` e, fora de pull requests, publica no GitHub Container Registry com as tags do commit e `latest` |
| `Deploy Staging` | Usa o environment `staging`, sobe a imagem publicada com o Docker Compose e valida cadastro e login na API |
| `Deploy Produção` | Usa o environment `production` e só roda depois do staging concluir com sucesso |

Em pull requests rodam build, testes e a geração da imagem. Os deploys rodam em push na `main` ou pela execução manual.

Os environments `staging` e `production` podem ser configurados em Settings > Environments. Neles é possível exigir aprovação manual antes do deploy e cadastrar os secrets `ORACLE_PASSWORD`, `DB_PASSWORD` e `JWT_SECRET` e a variável `DB_USER`. Sem esses valores o pipeline usa os padrões do `.env.example`.
