package br.com.financeiro.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Receita extends Transacao {

    public Receita(String descricao, BigDecimal valor, LocalDate data) {
        super(descricao, valor, data);
    }

    @Override
    public void aplicarEm(Conta conta) {
        conta.depositar(getValor());
    }
}
