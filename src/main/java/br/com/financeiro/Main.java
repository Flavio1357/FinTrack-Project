package br.com.financeiro;

import br.com.financeiro.controller.TransacaoController;
import br.com.financeiro.domain.Categoria;
import br.com.financeiro.domain.Conta;
import br.com.financeiro.pattern.AlertaConsole;
import br.com.financeiro.repository.TransacaoRepositoryMemoria;
import br.com.financeiro.service.TransacaoService;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Conta conta = new Conta("000.000.000-00", "Conta principal");
        TransacaoService service = new TransacaoService(conta, new TransacaoRepositoryMemoria());
        TransacaoController controller = new TransacaoController(service);

        Categoria alimentacao = new Categoria("Alimentação");
        Categoria mercado = new Categoria("Mercado");
        Categoria restaurantes = new Categoria("Restaurantes");
        alimentacao.adicionar(mercado);
        alimentacao.adicionar(restaurantes);

        service.definirOrcamento(alimentacao, new BigDecimal("500"), new AlertaConsole());

        controller.registrarReceita("Salário", "2000");
        controller.registrarDespesa("Compras do mês", "250", mercado);
        controller.registrarDespesa("Jantar", "160", restaurantes);
        controller.registrarDespesa("Almoço", "100", restaurantes);

        System.out.println(controller.saldo());
        System.out.print(controller.extrato());
    }
}
