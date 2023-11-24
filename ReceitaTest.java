import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReceitaTest {

    private Receita receita;
    
    @BeforeEach
    public void setUp() {
        receita = new Receita("SalÃ¡rio", 1000.00, "01/01/2023");
    }
 
    @Test
    public void testImpactoNoSaldoPositivo() {
        assertEquals(1000.00, receita.impactoNoSaldo(), "O impacto no saldo deve ser positivo para receitas.");
    }
 
    @Test
    public void testToString() {
        assertEquals("01/01/2023 - SalÃ¡rio: R$ 1000,00", receita.toString(), "toString deve retornar a string formatada corretamente.");
    }
 
    @Test
    public void testValorZero() {
        Receita receitaZero = new Receita("Investimento", 0.00, "01/01/2023");
        assertEquals(0.00, receitaZero.impactoNoSaldo(), "O impacto no saldo de uma receita de valor zero deve ser zero.");
    }
 
    @Test
    public void testValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Receita("Erro", -100.00, "01/01/2023");
        }, "Construir uma receita com valor negativo deve lanÃ§ar IllegalArgumentException.");
    }
    
    @Test
    public void testCategoriaNulaReceita() {
        assertThrows(IllegalArgumentException.class, () -> {    
        	new Receita(null, 1000.00, "01/01/2023");
        }, "Construir uma receita com categoria nula deve lançar IllegalArgumentException.");
    }
    
    @Test
    public void testDataNulaReceita() {
        assertThrows(IllegalArgumentException.class, () -> {       
        	new Receita("Salário", 1000.00, null);
        }, "Construir uma receita com data nula deve lançar IllegalArgumentException.");
    }
}
