package br.com.accenture.atividades;

public class Produto {
    public String sku;
    public String nome;
    public String categoria;
    public double preco;
    public int estoque;

    public Produto(String sku, String nome, String categoria, double preco, int estoque) {
        this.sku = sku;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }
}