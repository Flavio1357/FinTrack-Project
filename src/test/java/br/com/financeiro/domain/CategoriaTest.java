package br.com.financeiro.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class CategoriaTest {

    private Despesa despesa(String valor) {
        return new Despesa("teste", new BigDecimal(valor), LocalDate.now());
    }

    @Test
    void totalDeCategoriaVaziaEhZero() {
        Categoria alimentacao = new Categoria("Alimentação");
        assertEquals(0, BigDecimal.ZERO.compareTo(alimentacao.totalGasto()));
    }

    @Test
    void totalSomaAsDespesasDaCategoria() {
        Categoria mercado = new Categoria("Mercado");
        mercado.adicionar(despesa("50.00"));
        mercado.adicionar(despesa("25.50"));
        assertEquals(0, new BigDecimal("75.50").compareTo(mercado.totalGasto()));
    }

    @Test
    void totalIncluiAsSubcategorias() {
        Categoria alimentacao = new Categoria("Alimentação");
        Categoria mercado = new Categoria("Mercado");
        Categoria restaurantes = new Categoria("Restaurantes");
        alimentacao.adicionar(mercado);
        alimentacao.adicionar(restaurantes);

        mercado.adicionar(despesa("200.00"));
        restaurantes.adicionar(despesa("80.00"));
        alimentacao.adicionar(despesa("20.00"));

        assertEquals(0, new BigDecimal("300.00").compareTo(alimentacao.totalGasto()));
    }
}
