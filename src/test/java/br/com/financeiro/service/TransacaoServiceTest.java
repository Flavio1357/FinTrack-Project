package br.com.financeiro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.financeiro.domain.Categoria;
import br.com.financeiro.domain.Conta;
import br.com.financeiro.domain.NivelAlerta;
import br.com.financeiro.domain.TipoTransacao;
import br.com.financeiro.domain.Transacao;
import br.com.financeiro.repository.TransacaoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class TransacaoServiceTest {

    /** Repositório falso: só existe porque o service depende da interface (DIP). */
    private static class RepositorioFalso implements TransacaoRepository {
        final List<Transacao> salvas = new ArrayList<>();

        @Override
        public void salvar(Transacao transacao) {
            salvas.add(transacao);
        }

        @Override
        public List<Transacao> listarTodas() {
            return List.copyOf(salvas);
        }
    }

    private final RepositorioFalso repositorio = new RepositorioFalso();
    private final Conta conta = new Conta("000.000.000-00", "Teste");
    private final TransacaoService service = new TransacaoService(conta, repositorio);

    @Test
    void receitaAumentaOSaldoEEhSalva() {
        service.registrar(TipoTransacao.RECEITA, "Salário", new BigDecimal("1000"), null);

        assertEquals(0, new BigDecimal("1000").compareTo(service.saldo()));
        assertEquals(1, repositorio.salvas.size());
    }

    @Test
    void despesaDiminuiOSaldoEEntraNaCategoria() {
        Categoria mercado = new Categoria("Mercado");
        service.registrar(TipoTransacao.RECEITA, "Salário", new BigDecimal("1000"), null);

        service.registrar(TipoTransacao.DESPESA, "Feira", new BigDecimal("150"), mercado);

        assertEquals(0, new BigDecimal("850").compareTo(service.saldo()));
        assertEquals(0, new BigDecimal("150").compareTo(mercado.totalGasto()));
    }

    @Test
    void despesaQueChegaEm80PorCentoDisparaAlerta() {
        Categoria alimentacao = new Categoria("Alimentação");
        List<NivelAlerta> alertas = new ArrayList<>();
        service.definirOrcamento(alimentacao, new BigDecimal("100"), (o, nivel) -> alertas.add(nivel));

        service.registrar(TipoTransacao.DESPESA, "Mercado", new BigDecimal("85"), alimentacao);

        assertEquals(List.of(NivelAlerta.ATENCAO), alertas);
    }

    @Test
    void extratoListaAsTransacoesSalvas() {
        service.registrar(TipoTransacao.RECEITA, "Salário", new BigDecimal("1000"), null);
        service.registrar(TipoTransacao.RECEITA, "Freela", new BigDecimal("300"), null);

        assertEquals(2, service.extrato().size());
    }
}
