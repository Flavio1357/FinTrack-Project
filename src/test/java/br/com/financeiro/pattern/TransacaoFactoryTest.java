package br.com.financeiro.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.financeiro.domain.Despesa;
import br.com.financeiro.domain.Receita;
import br.com.financeiro.domain.TipoTransacao;
import br.com.financeiro.domain.Transacao;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class TransacaoFactoryTest {

    @Test
    void criaReceita() {
        Transacao t = TransacaoFactory.criar(TipoTransacao.RECEITA, "Salário", new BigDecimal("1000"));
        assertTrue(t instanceof Receita);
    }

    @Test
    void criaDespesa() {
        Transacao t = TransacaoFactory.criar(TipoTransacao.DESPESA, "Mercado", new BigDecimal("50"));
        assertTrue(t instanceof Despesa);
    }

    @Test
    void guardaDescricaoEValor() {
        Transacao t = TransacaoFactory.criar(TipoTransacao.DESPESA, "Mercado", new BigDecimal("50"));
        assertEquals("Mercado", t.getDescricao());
        assertEquals(0, new BigDecimal("50").compareTo(t.getValor()));
    }

    @Test
    void valorInvalidoEhRejeitado() {
        assertThrows(IllegalArgumentException.class,
                () -> TransacaoFactory.criar(TipoTransacao.RECEITA, "Erro", new BigDecimal("-1")));
    }
}
