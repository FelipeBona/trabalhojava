import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DespesaTest {

	private Despesa despesa;
	 
    @BeforeEach
    public void setUp() {
        despesa = new Despesa("Aluguel", 500.00, "01/02/2023");
    }
 
    @Test
    public void testImpactoNoSaldoNegativo() {
        assertEquals(-500.00, despesa.impactoNoSaldo(), "O impacto no saldo deve ser negativo para despesas.");
    }
 
    @Test
    public void testToString() {
        assertEquals("01/02/2023 - Aluguel: R$ -500,00", despesa.toString(), "toString deve retornar a string formatada corretamente.");
    }
 
    @Test
    public void testValorZero() {
        Despesa despesaZero = new Despesa("ServiÃ§os", 0.00, "01/02/2023");
        assertEquals(-0.00, despesaZero.impactoNoSaldo(), "O impacto no saldo de uma despesa de valor zero deve ser zero.");
    }
 
    @Test
    public void testValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Despesa("Erro", -100.00, "01/02/2023");
        }, "Construir uma despesa com valor negativo deve lanÃ§ar IllegalArgumentException.");
    }
    
    @Test
    public void testCategoriaNula() {
        assertThrows(IllegalArgumentException.class, () -> {   
        	new Despesa(null, 500.00, "01/02/2023");
        }, "Construir uma despesa com categoria nula deve lançar IllegalArgumentException.");
    }
    
    @Test
    public void testDataNula() {
        assertThrows(IllegalArgumentException.class, () -> {    
        	new Despesa("Aluguel", 500.00, null);
        }, "Construir uma despesa com data nula deve lançar IllegalArgumentException.");
    }
}
