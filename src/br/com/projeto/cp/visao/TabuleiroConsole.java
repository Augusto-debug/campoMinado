package br.com.projeto.cp.visao;

import br.com.projeto.cp.excecao.ExplosaoExcecao;
import br.com.projeto.cp.excecao.SairExcecao;
import br.com.projeto.cp.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);
    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while (continuar) {
                cicloDoJogo();
                System.out.println("Outra partida ? (S/n) ");
                String resposta = entrada.nextLine();
                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        }catch (SairExcecao e) {
            System.out.println("Tchau!!!");
        } finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);
                String digitado = capturarvalorDigitado("Digite (X,Y) : ");
                Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();
                digitado = capturarvalorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
                if ("1".equals(digitado)) {
                    tabuleiro.abrirTabuleiro(xy.next(), xy.next());
                } else if ("2".equals(digitado)) {
                    tabuleiro.marcar(xy.next(), xy.next());
                }
            }
            System.out.println("Voce ganhou");
        }catch (ExplosaoExcecao e) {
            System.out.println(tabuleiro);
            System.out.println("Voce perdeu");
        }
    }
    private String capturarvalorDigitado(String texto) {
        System.out.println(texto);
        String digitado = entrada.nextLine();
        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairExcecao();
        }
        return digitado;
    }
}
