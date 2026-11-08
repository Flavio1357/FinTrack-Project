package main.java.br.com.financeiro.domain;

/** Observer: quem quiser ser avisado quando um orçamento mudar de nível. */
@FunctionalInterface
public interface OrcamentoObserver {
    public void aoMudarNivel(Orcamento orcamento, NivelAlerta nivel);
}
