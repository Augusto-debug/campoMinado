package br.com.projeto.cp.modelo;
import br.com.projeto.cp.excecao.ExplosaoExcecao;

import java.util.ArrayList;
import java.util.List;
public class Campo {
    private boolean minado = false; // Default do tipo boolean.
    private boolean aberto = false;
    private boolean marcado;
    private final int linha;
    private final int coluna;
    private List<Campo> vizinhos = new ArrayList<>();
    public Campo ( int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;
        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;
        if(deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else{
            return false;
        }
    }
     public void alternarMarcado () {
        if (!aberto) {
            marcado = !marcado;
        }
    }
    public boolean abrir () {
        if(!aberto && !marcado) {
            aberto = true;
            if (minado) {
                throw new ExplosaoExcecao();
            }
            if (verificarVizinho()) {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean verificarVizinho() {
        return vizinhos.stream().noneMatch(campo -> campo.minado);
    }
    public boolean isMarcado() {
        return marcado;
    }
    public boolean isAberto() {
        return aberto;
    }
    public void minar() {
        minado = true;
    }
    public int getLinha() {
        return linha;
    }
    public int getColuna() {
        return coluna;
    }
    boolean objetivoAlcancado() {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }
    long minasNaVizinhanca() {
        return vizinhos.stream().filter(campo -> campo.minado).count();
    }
    void reiniciar() {
        minado = false;
        aberto = false;
        marcado = false;
    }
    public String toString() {
        if(marcado) {
            return "x";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minasNaVizinhanca() > 0) {
            return Long.toString(minasNaVizinhanca());
        } else if(aberto) {
            return " ";
        } else {
            return "?";
        }
    }
}
