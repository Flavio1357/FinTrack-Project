package br.com.financeiro.repository;

import br.com.financeiro.domain.Transacao;
import java.util.ArrayList;
import java.util.List;

public class TransacaoRepositoryMemoria implements TransacaoRepository {

    private final List<Transacao> transacoes = new ArrayList<>();

    @Override
    public void salvar(Transacao transacao) {
        transacoes.add(transacao);
    }

    @Override
    public List<Transacao> listarTodas() {
        return List.copyOf(transacoes);
    }
}
