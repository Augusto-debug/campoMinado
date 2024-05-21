package src;
import br.com.projeto.cp.excecao.ExplosaoExcecao;
import br.com.projeto.cp.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {
    private Campo campo;
    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }
    @Test
    public void testeVizinhoRealEsquerda() {
            Campo vizinho = new Campo(3, 2);
            boolean resultado = campo.adicionarVizinho(vizinho);
            assertTrue(resultado);
    }
    @Test
    public void testeVizinhoRealDireita() {
            Campo vizinho = new Campo(3, 4);
            boolean resultado = campo.adicionarVizinho(vizinho);
            assertTrue(resultado);
    }
    @Test
    public void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }
    @Test
    public void testeAlternarMarcacao() {
        campo.alternarMarcado();
        assertTrue(campo.isMarcado());
    }
    @Test
    public void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcado();
        campo.alternarMarcado();
        assertFalse(campo.isMarcado());
    }
    @Test
    public void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }
    @Test
    public void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcado();
        assertFalse(campo.abrir());
    }
    @Test
    public void testeAbrirMinadoMarcado() {
        campo.alternarMarcado();
        campo.minar();
        assertFalse(campo.abrir());
    }
    @Test
    public void testeAbrirMinadoNaoMarcado() {
        campo.minar();
        assertThrows(ExplosaoExcecao.class, () -> {
            campo.abrir();
        });
    }
}
