package br.com.accenture.atividades;

public class ItemPedido {
    public Produto produto;
    public int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
}