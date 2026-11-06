# Escola de Magia - Catálogo de Cursos

<p align="center">
  <a href="https://tenor.com/pt-BR/view/memes-gif-20407027">
    <img src="https://media1.tenor.com/m/IqPotHAztSYAAAAC/memes.gif" alt="Gandalf aprovando a Escola de Magia" width="498">
  </a>
</p>

Atividade independente de Java 21 e Spring Boot. O tema é uma escola de magia, preservando o modelo obrigatório `Instrutor 1:N Curso`.

**Alunos:** Cauã H. Oliveira e Leonardo Guilherme

## Evidências da entrega

- **Repositório:** https://github.com/CauaCHO/atividade-catalogo-cursos
- **Banco utilizado:** `catalogocursos_dev`
- **Comando de verificação:** `.\mvnw.cmd clean test`
- **Resultado:** 6 testes executados, 0 falhas, 0 erros e `BUILD SUCCESS`.

O hash solicitado na entrega deve ser o do commit mais recente do repositório. Ele pode ser consultado com `git rev-parse HEAD`.

## Domínio

- Instrutores são professores de magia.
- Cursos são disciplinas como Poções, Feitiços e Transfiguração.
- Um instrutor pode ministrar vários cursos.
- Cada curso possui obrigatoriamente um instrutor.

## Preparação do PostgreSQL

Crie o banco `catalogocursos_dev` e um usuário próprio. Copie `.env.example` para `.env` e ajuste `DB_DEV_URL`, `DB_DEV_USERNAME` e `DB_DEV_PASSWORD`. Não publique senhas reais.

## Executar pelo terminal do Postman

Abra o projeto no Postman, abra o terminal integrado na raiz do projeto e execute:

```powershell
.\mvnw.cmd spring-boot:run
```

O Spring Boot carrega automaticamente as configurações do arquivo `.env`. Mantenha o terminal executando enquanto utilizar as requisições.

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

