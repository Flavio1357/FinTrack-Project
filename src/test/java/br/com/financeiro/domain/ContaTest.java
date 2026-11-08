package br.com.financeiro.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ContaTest {

    private Conta novaConta() {
        return new Conta("000.000.000-00", "Principal");
    }

    @Test
    void contaNovaComecaComSaldoZero() {
        assertEquals(0, BigDecimal.ZERO.compareTo(novaConta().getSaldo()));
    }

    @Test
    void depositarAumentaOSaldo() {
        Conta conta = novaConta();
        conta.depositar(new BigDecimal("100.00"));
        assertEquals(0, new BigDecimal("100").compareTo(conta.getSaldo()));
    }

    @Test
    void retirarDiminuiOSaldo() {
        Conta conta = novaConta();
        conta.depositar(new BigDecimal("100.00"));
        conta.retirar(new BigDecimal("30.50"));
        assertEquals(0, new BigDecimal("69.50").compareTo(conta.getSaldo()));
    }
}
