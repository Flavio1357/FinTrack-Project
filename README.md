# Controle Financeiro — Bad Practices 

Esta versão foi criada a partir do projeto original para demonstrar **más práticas de programação**.
Ela é intencionalmente simplificada e concentrada em `Main.java`.

## Como executar
Com Java instalado (JDK 17+):

```bash
javac -d out src/main/java/br/com/financeiro/Main.java
java -cp out main.java.br.com.financeiro.Main
```

## Más práticas demonstradas
1. **God Class**: menu, regras financeiras, orçamento e extrato na mesma classe.
2. **Falta de encapsulamento**: saldo, gastos, limite e listas são `public`.
3. **Primitive Obsession**: dinheiro representado por `double`.
4. **Magic Numbers**: limites `0.80` e `1.00` espalhados no código.
5. **Duplicação**: operações de receita/despesa têm lógica repetida.
6. **Alto acoplamento**: interface, armazenamento e regras dependem diretamente de `Main`.
7. **Baixa coesão / SRP violado**: uma classe tem várias responsabilidades.
8. **Validação insuficiente**: aceita valores negativos, descrição vazia e categoria vazia.
9. **Tratamento de exceções ruim**: `catch (Exception)` genérico ignora o motivo do erro.
10. **Estruturas expostas**: listas mutáveis são acessíveis globalmente.
11. **Ausência de testes automatizados**: não há suíte de testes nesta versão.

> Esta implementação é apenas para estudo. Não use como sistema financeiro real.

## Comparação
O projeto original (`controle-financeiro-com-praticas`) contém a estrutura organizada e testes.
Esta versão (`controle-financeiro-bad-practices`) é o contraponto intencionalmente problemático.

