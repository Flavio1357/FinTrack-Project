package main.java.br.com.financeiro.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public abstract class Transacao {

    private final String descricao;
    private final BigDecimal valor;
    private final LocalDate data;

    protected Transacao(String descricao, BigDecimal valor, LocalDate data) {
        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero");
        }
        this.descricao = descricao;
        this.valor = valor.setScale(2, RoundingMode.HALF_EVEN);
        this.data = data;
    }

    /** Cada tipo de transação sabe como afetar o saldo da conta. */
    public abstract void aplicarEm(Conta conta);

    /** Por padrão não faz nada; só as despesas entram em categorias. */
    public void classificarEm(Categoria categoria) {
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }
}
