package br.com.accenture.atividades;

import java.util.Date;

public class PrincipalContaCorrente {
    public static void main(String[] args) {
        ContaCorrente conta1 = new ContaCorrente(101, "Melissa Gabriele", 1000.0, new Date());
        
        System.out.println("Cliente da Conta 1: " + conta1.nome);
        
        conta1.depositar(200.0);
        conta1.exibirExtrato();
        
        conta1.sacar(100.0);
        conta1.exibirExtrato();
        
        ContaCorrente conta2 = new ContaCorrente(102, "João Silva", 500.0, new Date());

        try {
            System.out.println("\nTentando realizar transferência...");
            conta1.transferir(1500.0, conta2); 
            System.out.println("Transferência realizada com sucesso!");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- Saldos Finais ---");
        System.out.print("Conta 1: ");
        conta1.exibirExtrato();
        System.out.print("Conta 2: ");
        conta2.exibirExtrato();
    }
}