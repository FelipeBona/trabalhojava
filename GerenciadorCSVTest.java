import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;


public class GerenciadorCSVTest {

	private static final String HEADER_CSV = "Tipo,Data,Categoria,Valor\n";
    private Path tempFile;
 
    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        tempFile = tempDir.resolve("transacoes_test.csv");
        Files.write(tempFile, HEADER_CSV.getBytes());
    }
 
    @Test
    public void testSalvarTransacaoReceita() throws IOException {
        Receita receita = new Receita("Salário", 1000.00, "01/01/2023");
        GerenciadorCSV.salvarTransacao(receita);
        List<String> lines = Files.readAllLines(tempFile);
        assertTrue(lines.contains("Receita ,01/01/2023, Salário, 1000.0"));
    }
 
    @Test
    public void testSalvarTransacaoDespesa() throws IOException {
        Despesa despesa = new Despesa("Aluguel", 500.00, "01/01/2023");
        GerenciadorCSV.salvarTransacao(despesa);
        List<String> lines = Files.readAllLines(tempFile);
        assertTrue(lines.contains("Despesa, 01/01/2023 ,Aluguel, 500.0"));
    }
 
    @Test
    public void testCarregarTransacoesVazio() {
        List<Transacao> transacoes = GerenciadorCSV.carregarTransacoes();
        assertTrue(transacoes.isEmpty(), "Nenhuma transação deve ser carregada de um arquivo CSV vazio.");
    }
 
    @Test
    public void testCarregarTransacoesNaoVazio() throws IOException {
        String transacaoString = "Receita , 02/01/2023, Investimento, 2000.0\nDespesa, 03/01/2023 , Internet, 100.0\n";
        Files.write(tempFile, transacaoString.getBytes(), StandardOpenOption.APPEND);
        List<Transacao> transacoes = GerenciadorCSV.carregarTransacoes();
        assertEquals(2, transacoes.size(), "Devem ser carregadas duas transações.");
    }
 
    @Test
    public void testFalhaAoSalvarTransacao() {
        assertThrows(IOException.class, () -> {
            GerenciadorCSV.salvarTransacao(new Receita("Salário", 1000.00, "01/01/2023"));
        });
    }
    
    @Test
    public void testFalhaAoCarregarTransacoes() throws IOException {
        Files.write(tempFile, "Dados inv�lidos".getBytes(), StandardOpenOption.APPEND);
        assertThrows(IOException.class, () -> {
            GerenciadorCSV.carregarTransacoes();
        }, "Falha ao carregar transa��es de um arquivo CSV inv�lido deve lan�ar IOException.");
    }
    
    @Test
    public void testSalvarTransacaoValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            GerenciadorCSV.salvarTransacao(new Despesa("Aluguel", -500.00, "01/01/2023"));
        }, "Falha ao salvar transa��o com valor negativo deve lan�ar IllegalArgumentException.");
    }
    
   
}
