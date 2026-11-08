package br.com.financeiro.controller;

import br.com.financeiro.domain.Categoria;
import br.com.financeiro.domain.TipoTransacao;
import br.com.financeiro.domain.Transacao;
import br.com.financeiro.service.TransacaoService;
import java.math.BigDecimal;

/** Recebe os pedidos do usuário (textos), converte e delega ao service. */
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    public void registrarReceita(String descricao, String valor) {
        service.registrar(TipoTransacao.RECEITA, descricao, converter(valor), null);
    }

    public void registrarDespesa(String descricao, String valor, Categoria categoria) {
        service.registrar(TipoTransacao.DESPESA, descricao, converter(valor), categoria);
    }

    public String saldo() {
        return "Saldo: R$ " + service.saldo().toPlainString();
    }

    public String extrato() {
        StringBuilder texto = new StringBuilder();
        for (Transacao t : service.extrato()) {
            texto.append(String.format("%s | %-8s | %-20s | R$ %s%n",
                    t.getData(), t.getClass().getSimpleName(),
                    t.getDescricao(), t.getValor().toPlainString()));
        }
        return texto.toString();
    }

    private BigDecimal converter(String valor) {
        return new BigDecimal(valor.replace(",", "."));
    }
}
