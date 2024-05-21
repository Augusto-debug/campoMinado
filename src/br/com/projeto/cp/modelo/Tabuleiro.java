package br.com.projeto.cp.modelo;
import br.com.projeto.cp.excecao.ExplosaoExcecao;

import java.util.ArrayList;
import java.util.List;
public class Tabuleiro {
    private int linhas;
    private int colunas;
    private int minas;
    private final List<Campo> campos = new ArrayList<>();
    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;
        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }
    public void abrirTabuleiro(int linha, int coluna  ) {
        try {
            campos.stream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                    .findFirst().ifPresent(campo -> campo.abrir());
        } catch (ExplosaoExcecao e) {
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }
    }
    public void marcar(int linha, int coluna  ) {
        campos.stream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                .findFirst().ifPresent(campo -> campo.alternarMarcado());
    }
    private void sortearMinas() {
        long minasArmadas = 0;
        do {
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(c -> c.isMinado()).count();
        } while (minasArmadas < minas);
    }
    private void associarVizinhos() {
        for(Campo c1 : campos) {
            for(Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }
    private void gerarCampos() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                campos.add(new Campo(i, j));
            }
        }
    }
    public boolean objetivoAlcancado() {
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }
    public void reiniciar() {
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < colunas; i++) {
            sb.append(" ");
            sb.append(i);
            sb.append(" ");
        }
        sb.append("\n");
        int n = 0;
        for (int i = 0; i < linhas; i++) {
            sb.append(i);
            sb.append(" ");
            for (int j = 0; j < colunas; j++) {
                sb.append(" ");
                sb.append(campos.get(n));
                sb.append(" ");
                n++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
