package br.com.financeiro.service;

import br.com.financeiro.domain.Categoria;
import br.com.financeiro.domain.Conta;
import br.com.financeiro.domain.Orcamento;
import br.com.financeiro.domain.OrcamentoObserver;
import br.com.financeiro.domain.TipoTransacao;
import br.com.financeiro.domain.Transacao;
import br.com.financeiro.pattern.TransacaoFactory;
import br.com.financeiro.repository.TransacaoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransacaoService {

    private final Conta conta;
    private final TransacaoRepository repositorio;
    private final List<Orcamento> orcamentos = new ArrayList<>();

    public TransacaoService(Conta conta, TransacaoRepository repositorio) {
        this.conta = conta;
        this.repositorio = repositorio;
    }

    public Orcamento definirOrcamento(Categoria categoria, BigDecimal limite,
                                      OrcamentoObserver observador) {
        Orcamento orcamento = new Orcamento(categoria, limite);
        orcamento.adicionarObservador(observador);
        orcamentos.add(orcamento);
        return orcamento;
    }

    public Transacao registrar(TipoTransacao tipo, String descricao,
                               BigDecimal valor, Categoria categoria) {
        Transacao transacao = TransacaoFactory.criar(tipo, descricao, valor);
        transacao.aplicarEm(conta);
        transacao.classificarEm(categoria);
        repositorio.salvar(transacao);
        orcamentos.forEach(Orcamento::verificar);
        return transacao;
    }

    public BigDecimal saldo() {
        return conta.getSaldo();
    }

    public List<Transacao> extrato() {
        return repositorio.listarTodas();
    }
}
