package br.com.accenture.atividades;

import java.util.Date;

public class ContaCorrente {
    public int numero;
    public String nome;
    public double saldo;
    public Date data;

    public ContaCorrente(int numero, String nome, double saldo, Date data) {
        this.numero = numero;
        this.nome = nome;
        this.saldo = saldo;
        this.data = data;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
    }

    public void exibirExtrato() {
        System.out.println("Saldo atual: R$ " + this.saldo);
    }

    public void transferir(double valor, ContaCorrente destino) throws Exception {
        if (this.saldo < valor) {
            throw new Exception("Transferência cancelada: Saldo insuficiente na conta de origem.");
        }
        this.sacar(valor);
        destino.depositar(valor);
    }
}