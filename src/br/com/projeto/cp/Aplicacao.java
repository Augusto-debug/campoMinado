package br.com.projeto.cp;

import br.com.projeto.cp.modelo.Tabuleiro;
import br.com.projeto.cp.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        new TabuleiroConsole(tabuleiro);
    }
}
