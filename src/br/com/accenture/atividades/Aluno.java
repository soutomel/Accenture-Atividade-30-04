package br.com.accenture.atividades;

public class Aluno {
    public String nome;
    public double[] notas = new double[3];
    public double media;

    public Aluno(String nome, double n1, double n2, double n3) {
        this.nome = nome;
        this.notas[0] = n1;
        this.notas[1] = n2;
        this.notas[2] = n3;
        this.media = (n1 + n2 + n3) / 3;
    }

    public String getStatus() {
        int faixa = (media >= 70) ? 1 : (media >= 50) ? 2 : 3;

        return switch (faixa) {
            case 1 -> "APROVADO";
            case 2 -> "RECUPERAÇÃO";
            default -> "REPROVADO";
        };
    }
}