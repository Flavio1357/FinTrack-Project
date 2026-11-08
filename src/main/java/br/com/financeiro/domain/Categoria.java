package br.com.financeiro.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Composite: uma categoria contém despesas e outras categorias. */
public class Categoria implements ItemGasto {

    private final String nome;
    private final List<ItemGasto> itens = new ArrayList<>();

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void adicionar(ItemGasto item) {
        itens.add(item);
    }

    @Override
    public BigDecimal totalGasto() {
        BigDecimal total = BigDecimal.ZERO.setScale(2);
        for (ItemGasto item : itens) {
            total = total.add(item.totalGasto());
        }
        return total;
    }

    public String getNome() {
        return nome;
    }
}
