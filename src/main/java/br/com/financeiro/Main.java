package main.java.br.com.financeiro;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Má prática: atributos públicos e estado global/mutável
    public static double saldo = 0;
    public static double gastos = 0;
    public static double limite = 500;
    public static ArrayList<String> extrato = new ArrayList<>();
    public static ArrayList<String> categorias = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        // Má prática: método principal concentra interface e regras
        while (opcao != 6) {
            System.out.println("\\n===== CONTROLE FINANCEIRO =====");
            System.out.println("1 - Receita");
            System.out.println("2 - Despesa");
            System.out.println("3 - Ver saldo");
            System.out.println("4 - Ver extrato");
            System.out.println("5 - Ver orçamento");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                opcao = 0; // Má prática: captura genérica e ignora o erro
            }

            if (opcao == 1) {
                System.out.print("Descrição: ");
                String descricao = sc.nextLine();
                System.out.print("Valor: ");
                double valor = Double.parseDouble(sc.nextLine().replace(",", "."));

                // Má prática: não valida valor nem descrição
                saldo = saldo + valor;
                extrato.add("RECEITA | " + descricao + " | R$ " + valor);
                System.out.println("Receita registrada!");
            } else if (opcao == 2) {
                System.out.print("Descrição: ");
                String descricao = sc.nextLine();
                System.out.print("Valor: ");
                double valor = Double.parseDouble(sc.nextLine().replace(",", "."));
                System.out.print("Categoria: ");
                String categoria = sc.nextLine();

                // Má prática: regras duplicadas e sem validação
                saldo = saldo - valor;
                gastos = gastos + valor;
                extrato.add("DESPESA | " + descricao + " | R$ " + valor + " | " + categoria);
                categorias.add(categoria);

                // Má prática: números mágicos e lógica de alerta no menu
                if (gastos >= limite * 0.80 && gastos < limite) {
                    System.out.println("ATENÇÃO: você gastou 80% do orçamento!");
                }
                if (gastos >= limite * 1.00) {
                    System.out.println("ORÇAMENTO ESTOURADO!");
                }
                System.out.println("Despesa registrada!");
            } else if (opcao == 3) {
                System.out.println("Saldo: R$ " + saldo);
            } else if (opcao == 4) {
                // Má prática: apresentação diretamente sobre estrutura interna
                for (int i = 0; i < extrato.size(); i++) {
                    System.out.println(extrato.get(i));
                }
            } else if (opcao == 5) {
                System.out.println("Gastos: R$ " + gastos + " / Limite: R$ " + limite);
                System.out.println("Percentual: " + (gastos / limite * 100) + "%");
            } else if (opcao != 6) {
                System.out.println("Opção inválida!");
            }
        }

        sc.close();
        System.out.println("Encerrado.");
    }
}

