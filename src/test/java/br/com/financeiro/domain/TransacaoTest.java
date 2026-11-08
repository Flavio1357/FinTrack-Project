package br.com.financeiro.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TransacaoTest {

    private Conta novaConta() {
        return new Conta("000.000.000-00", "Principal");
    }

    @Test
    void receitaAumentaOSaldoDaConta() {
        Conta conta = novaConta();
        Transacao receita = new Receita("Salário", new BigDecimal("1000"), LocalDate.now());

        receita.aplicarEm(conta);

        assertEquals(0, new BigDecimal("1000").compareTo(conta.getSaldo()));
    }

    @Test
    void despesaDiminuiOSaldoDaConta() {
        Conta conta = novaConta();
        conta.depositar(new BigDecimal("500"));
        Transacao despesa = new Despesa("Mercado", new BigDecimal("120"), LocalDate.now());

        despesa.aplicarEm(conta);

        assertEquals(0, new BigDecimal("380").compareTo(conta.getSaldo()));
    }

    @Test
    void despesaEntraNaCategoriaAoSerClassificada() {
        Categoria mercado = new Categoria("Mercado");
        Transacao despesa = new Despesa("Feira", new BigDecimal("40"), LocalDate.now());

        despesa.classificarEm(mercado);

        assertEquals(0, new BigDecimal("40").compareTo(mercado.totalGasto()));
    }

    @Test
    void valorZeroOuNegativoEhRejeitado() {
        assertThrows(IllegalArgumentException.class,
                () -> new Receita("Inválida", BigDecimal.ZERO, LocalDate.now()));
        assertThrows(IllegalArgumentException.class,
                () -> new Despesa("Inválida", new BigDecimal("-5"), LocalDate.now()));
    }
}
