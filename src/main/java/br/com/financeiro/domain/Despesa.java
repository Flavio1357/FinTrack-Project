package br.com.financeiro.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Folha do Composite: uma despesa é um item de gasto. */
public class Despesa extends Transacao implements ItemGasto {

    public Despesa(String descricao, BigDecimal valor, LocalDate data) {
        super(descricao, valor, data);
    }

    @Override
    public void aplicarEm(Conta conta) {
        conta.retirar(getValor());
    }

    @Override
    public void classificarEm(Categoria categoria) {
        Objects.requireNonNull(categoria, "Toda despesa precisa de uma categoria");
        categoria.adicionar(this);
    }

    @Override
    public BigDecimal totalGasto() {
        return getValor();
    }
}
