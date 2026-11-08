package br.com.financeiro.domain;

import java.math.BigDecimal;

/** Componente do Composite: algo cujo gasto total pode ser calculado. */
public interface ItemGasto {
    BigDecimal totalGasto();
}
