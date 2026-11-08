package br.com.financeiro.pattern;

import br.com.financeiro.domain.NivelAlerta;
import br.com.financeiro.domain.Orcamento;
import br.com.financeiro.domain.OrcamentoObserver;

/** Observador concreto: mostra o alerta no console. */
public class AlertaConsole implements OrcamentoObserver {

    @Override
    public void aoMudarNivel(Orcamento orcamento, NivelAlerta nivel) {
        System.out.println("ALERTA [" + nivel + "] Orçamento de "
                + orcamento.getCategoria().getNome()
                + " está em " + orcamento.percentualGasto() + "% do limite");
    }
}
