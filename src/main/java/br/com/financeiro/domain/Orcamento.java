package main.java.br.com.financeiro.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/** Limite de gasto de uma categoria. Avisa os observadores quando o nível muda. */
public class Orcamento {

    private static final BigDecimal CEM = new BigDecimal("100");
    private static final BigDecimal LIMIAR_ATENCAO = new BigDecimal("80");

    private final Categoria categoria;
    private final BigDecimal limite;
    private final List<OrcamentoObserver> observadores = new ArrayList<>();
    private NivelAlerta nivelAtual = NivelAlerta.NORMAL;

    public Orcamento(Categoria categoria, BigDecimal limite) {
        if (limite == null || limite.signum() <= 0) {
            throw new IllegalArgumentException("O limite deve ser maior que zero");
        }
        this.categoria = categoria;
        this.limite = limite;
    }

    public void adicionarObservador(OrcamentoObserver observador) {
        observadores.add(observador);
    }

    public BigDecimal percentualGasto() {
        return categoria.totalGasto()
                .multiply(CEM)
                .divide(limite, 2, RoundingMode.HALF_EVEN);
    }

    /** Recalcula o nível e só notifica se ele mudou desde a última verificação. */
    public void verificar() {
        NivelAlerta novoNivel = calcularNivel();
        if (novoNivel != nivelAtual) {
            nivelAtual = novoNivel;
            notificar();
        }
    }

    private NivelAlerta calcularNivel() {
        BigDecimal percentual = percentualGasto();
        if (percentual.compareTo(CEM) >= 0) {
            return NivelAlerta.ESTOURADO;
        }
        if (percentual.compareTo(LIMIAR_ATENCAO) >= 0) {
            return NivelAlerta.ATENCAO;
        }
        return NivelAlerta.NORMAL;
    }

    private void notificar() {
        for (OrcamentoObserver observador : observadores) {
            observador.aoMudarNivel(this, nivelAtual);
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public NivelAlerta getNivelAtual() {
        return nivelAtual;
    }
}