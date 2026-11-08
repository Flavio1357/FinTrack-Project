# Controle Financeiro - versão COM boas práticas

Versão organizada do sistema, para comparar com `controle-financeiro-sem-praticas`.
As duas fazem exatamente a mesma coisa (mesma saída no `Main`).

## Estrutura (arquitetura em camadas)
- `controller/` recebe os pedidos e delega
- `service/` coordena as operações
- `domain/` regras de negócio (Conta, Transacao, Receita, Despesa, Categoria, Orcamento...)
- `pattern/` classes dos padrões GoF (Factory e observador concreto)
- `repository/` guarda os dados atrás de uma interface

## Boas práticas aplicadas
- Padrões: Factory (TransacaoFactory), Composite (Categoria/ItemGasto/Despesa), Observer (Orcamento/OrcamentoObserver)
- SOLID: S, O, L, I e D (o Service depende da interface TransacaoRepository)
- GRASP: Especialista na Informação e Controlador
- Testes de unidade (JUnit 5) em `src/test`; cobertura com JaCoCo (`mvn test`)

## Como rodar
- Testes: `mvn test` (relatório de cobertura em `target/site/jacoco/index.html`)
- Demonstração: executar a classe `br.com.financeiro.Main` pelo VS Code

## Sobre o pacote (`br.com.financeiro`)
Esta versão usa o pacote `br.com.financeiro` (e `br.com.financeiro` nos testes),
que é o que o VS Code aceita quando a pasta `src` é tratada como raiz do código.
Se um dia o projeto for importado como projeto Maven, o VS Code vai avisar que o pacote não bate com a
pasta; nesse caso basta trocar `br.com.financeiro` por `br.com.financeiro` (localizar e
substituir em todos os arquivos) e `br.com.financeiro` por `br.com.financeiro`.
