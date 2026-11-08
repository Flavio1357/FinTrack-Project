package br.com.financeiro.pattern;

import br.com.financeiro.domain.Despesa;
import br.com.financeiro.domain.Receita;
import br.com.financeiro.domain.TipoTransacao;
import br.com.financeiro.domain.Transacao;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Factory: cria a transação certa sem o cliente conhecer as subclasses. */
public class TransacaoFactory {

    private TransacaoFactory() {
    }

    public static Transacao criar(TipoTransacao tipo, String descricao, BigDecimal valor) {
        LocalDate hoje = LocalDate.now();
        return switch (tipo) {
            case RECEITA -> new Receita(descricao, valor, hoje);
            case DESPESA -> new Despesa(descricao, valor, hoje);
        };
    }
}
