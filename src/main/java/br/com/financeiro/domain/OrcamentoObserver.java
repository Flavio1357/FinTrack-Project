package br.com.financeiro.domain;

/** Observer: quem quiser ser avisado quando um orçamento mudar de nível. */
@FunctionalInterface
public interface OrcamentoObserver {
    void aoMudarNivel(Orcamento orcamento, NivelAlerta nivel);
}
