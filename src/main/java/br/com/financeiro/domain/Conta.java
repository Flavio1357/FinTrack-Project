package br.com.financeiro.domain;

import java.math.BigDecimal;

public class Conta {

    private final String cpf;
    private final String nome;
    private BigDecimal saldo = BigDecimal.ZERO.setScale(2);

    public Conta(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public void depositar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    public void retirar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
