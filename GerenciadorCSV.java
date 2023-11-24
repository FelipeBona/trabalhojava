import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
 
public class GerenciadorCSV {
 
    private static final String ARQUIVO_CSV = "transacoes.csv";
 
    public static void salvarTransacao(Transacao transacao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CSV, true))) {
            writer.write(transacaoToCSV(transacao));
            writer.newLine();
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Erro ao salvar a transação no arquivo.");
        }
    }
 
    public static List<Transacao> carregarTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                transacoes.add(csvToTransacao(linha));
            }
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Erro ao salvar a transação no arquivo.");
        }
        return transacoes;
    }
 
    private static String transacaoToCSV(Transacao transacao) {
        String tipo = transacao instanceof Receita ? "R" : "D";
        return tipo + "," + transacao.getData() + "," + transacao.getCategoria() + "," + transacao.getValor();
    }
 
    private static Transacao csvToTransacao(String linha) {
        String[] campos = linha.split(",");
        String tipo = campos[0];
        String data = campos[1];
        String categoria = campos[2];
        double valor = Double.parseDouble(campos[3]);
 
        if ("R".equals(tipo)) {
            return new Receita(categoria, valor, data);
        } else {
            return new Despesa(categoria, valor, data);
        }
    }
}