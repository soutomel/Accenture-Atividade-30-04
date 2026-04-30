package br.com.accenture.atividades;

import java.util.*;

public class Sistema {

    public Map<String, Produto> produtos = new HashMap<>();
    public Map<String, Cliente> clientes = new HashMap<>();
    public Map<Integer, Pedido> pedidos = new HashMap<>();
    public int pedidoId = 1;

    Scanner sc = new Scanner(System.in);


    public void adicionarProduto() {
        System.out.print("SKU: ");
        String sku = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Preço: ");
        double preco = Double.parseDouble(sc.nextLine());

        System.out.print("Estoque: ");
        int estoque = Integer.parseInt(sc.nextLine());

        produtos.put(sku, new Produto(sku, nome, categoria, preco, estoque));
        System.out.println("✅ Produto cadastrado!");
    }

    public void listarProdutos() {
        System.out.print("Ordenar por (sku/preco): ");
        String tipo = sc.nextLine();

        List<Produto> lista = new ArrayList<>(produtos.values());

        if (tipo.equalsIgnoreCase("preco")) {
            lista.sort(Comparator.comparingDouble(p -> p.preco));
        } else {
            lista.sort(Comparator.comparing(p -> p.sku));
        }

        for (Produto p : lista) {
            System.out.println(p.sku + " | " + p.nome + " | " + p.preco + " | estoque: " + p.estoque);
        }
    }


    public void adicionarCliente() {
        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        clientes.put(id, new Cliente(id, nome));
        System.out.println("✅ Cliente cadastrado!");
    }


    public void criarPedido() {
        System.out.print("Cliente ID: ");
        String clienteId = sc.nextLine();

        Cliente cliente = clientes.get(clienteId);
        if (cliente == null) {
            System.out.println("❌ Cliente não encontrado!");
            return;
        }

        Pedido pedido = new Pedido(pedidoId++, cliente);

        while (true) {
            System.out.print("SKU (ENTER para sair): ");
            String sku = sc.nextLine();
            if (sku.isEmpty()) break;

            Produto p = produtos.get(sku);
            if (p == null) {
                System.out.println("Produto não existe!");
                continue;
            }

            System.out.print("Quantidade: ");
            int qtd = Integer.parseInt(sc.nextLine());

            pedido.adicionarItem(p, qtd);
        }

        pedidos.put(pedido.id, pedido);
        System.out.println("✅ Pedido criado!");
    }

    public void reservarEstoque() {
        System.out.print("ID do pedido: ");
        int id = Integer.parseInt(sc.nextLine());

        Pedido pedido = pedidos.get(id);

        for (ItemPedido item : pedido.itens) {
            if (item.produto.estoque < item.quantidade) {
                System.out.println("❌ Estoque insuficiente!");
                return;
            }
        }

        for (ItemPedido item : pedido.itens) {
            item.produto.estoque -= item.quantidade;
        }

        pedido.calcularTotal();
        pedido.status = "RESERVED";

        System.out.println("✅ Estoque reservado!");
    }

    public void pagarPedido() {
        System.out.print("ID do pedido: ");
        int id = Integer.parseInt(sc.nextLine());

        Pedido pedido = pedidos.get(id);

        if (!pedido.status.equals("RESERVED")) {
            System.out.println("❌ Pedido não reservado!");
            return;
        }

        System.out.print("Pagamento aprovado? (s/n): ");
        String resp = sc.nextLine();

        if (resp.equalsIgnoreCase("s")) {
            pedido.status = "PAID";
        } else {
            pedido.status = "FAILED";
        }

        System.out.println("Status: " + pedido.status);
    }

    public void cancelarPedido() {
        System.out.print("ID do pedido: ");
        int id = Integer.parseInt(sc.nextLine());

        Pedido pedido = pedidos.get(id);

        if (pedido.status.equals("RESERVED")) {
            for (ItemPedido item : pedido.itens) {
                item.produto.estoque += item.quantidade;
            }
        }

        pedido.status = "CANCELED";
        System.out.println("❌ Pedido cancelado!");
    }


    public void relatorios() {
        double faturamento = 0;
        Map<String, Integer> vendas = new HashMap<>();
        Map<String, Double> categorias = new HashMap<>();
        Map<String, Integer> clientesCount = new HashMap<>();

        for (Pedido p : pedidos.values()) {
            if (p.status.equals("PAID")) {
                faturamento += p.total;

                clientesCount.put(p.cliente.nome,
                        clientesCount.getOrDefault(p.cliente.nome, 0) + 1);

                for (ItemPedido item : p.itens) {
                    vendas.put(item.produto.nome,
                            vendas.getOrDefault(item.produto.nome, 0) + item.quantidade);

                    categorias.put(item.produto.categoria,
                            categorias.getOrDefault(item.produto.categoria, 0.0)
                                    + item.produto.preco * item.quantidade);
                }
            }
        }

        System.out.println("\nFaturamento: " + faturamento);

        System.out.println("\nTop 3 produtos:");
        vendas.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(3)
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));

        System.out.println("\nPor categoria:");
        categorias.forEach((k, v) -> System.out.println(k + " - " + v));

        System.out.println("\nClientes com mais pedidos:");
        clientesCount.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
    }
}