# Escola de Magia - Catálogo de Cursos

Atividade independente de Java 21 e Spring Boot. O tema é uma escola de magia, preservando o modelo obrigatório `Instrutor 1:N Curso`.

## Domínio

- Instrutores são professores de magia.
- Cursos são disciplinas como Poções, Feitiços e Transfiguração.
- Um instrutor pode ministrar vários cursos.
- Cada curso possui obrigatoriamente um instrutor.

## Preparação do PostgreSQL

Crie o banco `catalogocursos_dev` e um usuário próprio. Copie `.env.example` para `.env` e ajuste `DB_DEV_URL`, `DB_DEV_USERNAME` e `DB_DEV_PASSWORD`. Não publique senhas reais.

## Executar

No Windows, dê dois cliques em `INICIAR-SISTEMA.bat` ou execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Importe `postman/Escola-de-Magia.postman_collection.json` no Postman.

## Endpoints

| Método | Endpoint | Função |
|---|---|---|
| POST | `/api/instrutores` | Cadastrar professor |
| POST | `/api/cursos` | Cadastrar curso com instrutor existente |
| GET | `/api/cursos/{id}` | Consultar curso |
| GET | `/api/instrutores/{id}/cursos` | Listar cursos do instrutor por título |

## Testes

```powershell
.\mvnw.cmd clean test
```

## Respostas para a entrega

- O lado proprietário é `Curso`, pois contém a associação `ManyToOne`.
- A chave estrangeira `instrutor_id` fica na tabela `curso`.
- Entidades não são retornadas diretamente para evitar acoplamento e recursão JSON; a API usa DTOs.
