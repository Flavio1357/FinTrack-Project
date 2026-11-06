package main.domain;

import java.math.BigDecimal;

public class Conta {

    private String cpf;
    private String nome;    
    private BigDecimal saldo = BigDecimal.ZERO.setScale(2);

    public Conta(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }

    public void depositar(BigDecimal valor){
        saldo = saldo.add(valor);
    }

    public void retirar(BigDecimal valor){
        saldo = saldo.subtract(valor);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
