package br.com.financeiro.repository;

import br.com.financeiro.domain.Transacao;
import java.util.List;

public interface TransacaoRepository {
    void salvar(Transacao transacao);

    List<Transacao> listarTodas();
}
