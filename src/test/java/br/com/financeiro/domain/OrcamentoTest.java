package br.com.financeiro.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrcamentoTest {

    private final Categoria categoria = new Categoria("Alimentação");
    private final List<NivelAlerta> alertas = new ArrayList<>();

    private Orcamento orcamentoDe100() {
        Orcamento orcamento = new Orcamento(categoria, new BigDecimal("100"));
        orcamento.adicionarObservador((o, nivel) -> alertas.add(nivel));
        return orcamento;
    }

    private void gastar(String valor) {
        categoria.adicionar(new Despesa("teste", new BigDecimal(valor), LocalDate.now()));
    }

    @Test
    void semAlertaAbaixoDe80PorCento() {
        Orcamento orcamento = orcamentoDe100();
        gastar("79");
        orcamento.verificar();
        assertEquals(List.of(), alertas);
    }

    @Test
    void alertaDeAtencaoAoChegarEm80PorCento() {
        Orcamento orcamento = orcamentoDe100();
        gastar("80");
        orcamento.verificar();
        assertEquals(List.of(NivelAlerta.ATENCAO), alertas);
    }

    @Test
    void alertaDeEstouroAoChegarEm100PorCento() {
        Orcamento orcamento = orcamentoDe100();
        gastar("100");
        orcamento.verificar();
        assertEquals(List.of(NivelAlerta.ESTOURADO), alertas);
    }

    @Test
    void naoRepeteOAlertaDoMesmoNivel() {
        Orcamento orcamento = orcamentoDe100();
        gastar("80");
        orcamento.verificar();
        gastar("5");
        orcamento.verificar();
        assertEquals(List.of(NivelAlerta.ATENCAO), alertas);
    }

    @Test
    void passaDeAtencaoParaEstouro() {
        Orcamento orcamento = orcamentoDe100();
        gastar("85");
        orcamento.verificar();
        gastar("20");
        orcamento.verificar();
        assertEquals(List.of(NivelAlerta.ATENCAO, NivelAlerta.ESTOURADO), alertas);
    }

    @Test
    void calculaOPercentualGasto() {
        Orcamento orcamento = orcamentoDe100();
        gastar("25");
        assertEquals(0, new BigDecimal("25").compareTo(orcamento.percentualGasto()));
    }

    @Test
    void limiteInvalidoEhRejeitado() {
        assertThrows(IllegalArgumentException.class,
                () -> new Orcamento(categoria, BigDecimal.ZERO));
    }
}
