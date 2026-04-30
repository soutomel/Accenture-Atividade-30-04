package br.com.accenture.atividades;

import java.util.*;

public class SistemaEscolar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Aluno> turma = new ArrayList<>();

        System.out.println("--- Cadastro de 5 Alunos ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("\nAluno " + i + ":");
            String nome = lerNome(sc, "Nome: ");
            double n1 = lerNota(sc, "Nota 1: ");
            double n2 = lerNota(sc, "Nota 2: ");
            double n3 = lerNota(sc, "Nota 3: ");
            turma.add(new Aluno(nome, n1, n2, n3));
        }

        
        System.out.println("\n[1] Relatório Individual");
        turma.forEach(a -> System.out.printf("%s | Notas: %.1f, %.1f, %.1f | Média: %.2f | %s%n", 
            a.nome, a.notas[0], a.notas[1], a.notas[2], a.media, a.getStatus()));

        
        double maior = turma.stream().mapToDouble(a -> a.media).max().orElse(0);
        double menor = turma.stream().mapToDouble(a -> a.media).min().orElse(0);
        double geral = turma.stream().mapToDouble(a -> a.media).average().orElse(0);

        System.out.println("\n[2] Estatísticas da Turma");
        System.out.printf("Maior média: %.2f%n", maior);
        System.out.printf("Menor média: %.2f%n", menor);
        System.out.printf("Média geral da turma: %.2f%n", geral);
             
        System.out.println("\n[3] Distribuição de Resultados");
        System.out.println("APROVADOS: " + turma.stream().filter(a -> a.getStatus().equals("APROVADO")).count());
        System.out.println("RECUPERAÇÃO: " + turma.stream().filter(a -> a.getStatus().equals("RECUPERAÇÃO")).count());
        System.out.println("REPROVADOS: " + turma.stream().filter(a -> a.getStatus().equals("REPROVADO")).count());

        System.out.println("\n[4] Melhor(es) Aluno(s)");
        turma.stream()
             .filter(a -> a.media == maior)
             .forEach(a -> System.out.println("Nome: " + a.nome + " (Média: " + a.media + ")"));
    }

    private static String lerNome(Scanner sc, String msg) {
        System.out.print(msg);
        String entrada = sc.nextLine();
        return (entrada.length() >= 3) ? entrada : lerNome(sc, "Inválido! Mínimo 3 caracteres: ");
    }

    private static double lerNota(Scanner sc, String msg) {
        System.out.print(msg);
        try {
            double n = Double.parseDouble(sc.nextLine());
            return (n >= 0 && n <= 100) ? n : lerNota(sc, "Nota inválida (0-100)! Digite novamente: ");
        } catch (Exception e) {
            return lerNota(sc, "Entrada inválida! Digite um número para a nota: ");
        }
    }
}