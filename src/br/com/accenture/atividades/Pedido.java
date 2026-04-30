package br.com.accenture.atividades;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    public int id;
    public Cliente cliente;
    public List<ItemPedido> itens = new ArrayList<>();
    public String status = "CREATED";
    public double total = 0;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public void adicionarItem(Produto produto, int qtd) {
        itens.add(new ItemPedido(produto, qtd));
    }

    public void calcularTotal() {
        total = 0;

        for (ItemPedido item : itens) {
            total += item.produto.preco * item.quantidade;
        }

        if (total > 100) {
            total *= 0.9;
        }
    }
}