package br.com.accenture.atividades;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
1 - Adicionar produto
2 - Listar produtos
3 - Adicionar cliente
4 - Criar pedido
5 - Reservar estoque
6 - Pagar pedido
7 - Cancelar pedido
8 - Relatórios
0 - Sair
""");

            String op = sc.nextLine();

            switch (op) {
                case "1" -> sistema.adicionarProduto();
                case "2" -> sistema.listarProdutos();
                case "3" -> sistema.adicionarCliente();
                case "4" -> sistema.criarPedido();
                case "5" -> sistema.reservarEstoque();
                case "6" -> sistema.pagarPedido();
                case "7" -> sistema.cancelarPedido();
                case "8" -> sistema.relatorios();
                case "0" -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }
    }
}