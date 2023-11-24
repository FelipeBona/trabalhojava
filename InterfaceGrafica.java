import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class InterfaceGrafica {
 
    private static JFrame frame;
    private static JTextArea txtArea;
    private static JComboBox<String> comboCategoria;
    private static JTextField txtValor;
    private static JTextField txtData;
 
    public static void main(String[] args) {
        frame = new JFrame("Gerenciador Financeiro");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
 
        // Text area para listar transações
        txtArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setBounds(20, 20, 540, 200);
        frame.add(scroll);
 
        // Campo para inserir data
        JLabel labelData = new JLabel("Data (dd/mm/yyyy):");
        labelData.setBounds(20, 230, 150, 20);
        frame.add(labelData);
 
        txtData = new JTextField();
        txtData.setBounds(170, 230, 100, 20);
        frame.add(txtData);
 
        // ComboBox para selecionar categoria
        comboCategoria = new JComboBox<>(new String[]{"Salário", "Décimo terceiro", "Férias", "Alimentação", "Transporte", "Residência", "Saúde", "Educação", "Entretenimento", "Outras"});
        comboCategoria.setBounds(20, 260, 150, 20);
        frame.add(comboCategoria);
 
        // Campo para inserir valor
        JLabel labelValor = new JLabel("Valor:");
        labelValor.setBounds(180, 260, 40, 20);
        frame.add(labelValor);
 
        txtValor = new JTextField();
        txtValor.setBounds(230, 260, 100, 20);
        frame.add(txtValor);
 
        // Botão para adicionar receita
        JButton btnReceita = new JButton("Adicionar Receita");
        btnReceita.setBounds(20, 290, 150, 30);
        btnReceita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarReceita();
            }
        });
        frame.add(btnReceita);
 
        // Botão para adicionar despesa
        JButton btnDespesa = new JButton("Adicionar Despesa");
        btnDespesa.setBounds(180, 290, 150, 30);
        btnDespesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarDespesa();
            }
        });
        frame.add(btnDespesa);
 
        // Botão para filtrar despesas por categoria
        JButton btnFiltrar = new JButton("Filtrar Despesas");
        btnFiltrar.setBounds(340, 290, 150, 30);
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarDespesasPorCategoria((String) comboCategoria.getSelectedItem());
            }
        });
        frame.add(btnFiltrar);
 
        frame.setVisible(true);
 
        // Carregar transações iniciais
        atualizarTextArea();
    }
    
 
    private static void adicionarReceita() {
        try {
            String categoria = (String) comboCategoria.getSelectedItem();
            double valor = Double.parseDouble(txtValor.getText());
            String data = txtData.getText();
            Receita receita = new Receita(categoria, valor, data);
            GerenciadorCSV.salvarTransacao(receita);
            atualizarTextArea();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar receita. Verifique os dados inseridos.");
        }
    }
 
    private static void adicionarDespesa() {
        try {
            String categoria = (String) comboCategoria.getSelectedItem();
            double valor = Double.parseDouble(txtValor.getText());
            String data = txtData.getText();
            Despesa despesa = new Despesa(categoria, valor, data);
            GerenciadorCSV.salvarTransacao(despesa);
            atualizarTextArea();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar despesa. Verifique os dados inseridos.");
        }
    }
    
    private static void consultarSaldoAtual() {
        List<Transacao> transacoes = GerenciadorCSV.carregarTransacoes();
        double saldoAtual = 0.0;
        for (Transacao transacao : transacoes) {
            if (transacao.getData().compareTo(getDataAtual()) <= 0) {
                saldoAtual += transacao.impactoNoSaldo();
            }
        }
        JOptionPane.showMessageDialog(frame, "Saldo atual: R$ " + saldoAtual);
    }
 
    private static void consultarSaldoTotal() {
        List<Transacao> transacoes = GerenciadorCSV.carregarTransacoes();
        double saldoTotal = 0.0;
        for (Transacao transacao : transacoes) {
            saldoTotal += transacao.impactoNoSaldo();
        }
        JOptionPane.showMessageDialog(frame, "Saldo total: R$ " + saldoTotal);
    }
 
    private static String getDataAtual() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
 
    private static void atualizarTextArea() {
        List<Transacao> transacoes = GerenciadorCSV.carregarTransacoes();
        Collections.sort(transacoes, Comparator.comparing(Transacao::getData));
        StringBuilder sb = new StringBuilder();
        double saldo = 0.0;
        for (Transacao transacao : transacoes) {
            saldo += transacao.impactoNoSaldo();
            sb.append(transacao.toString()).append(" - Saldo: R$ ").append(saldo).append("\n");
        }
        txtArea.setText(sb.toString());
    }
 
    private static void listarDespesasPorCategoria(String categoria) {
        List<Transacao> transacoes = GerenciadorCSV.carregarTransacoes();
        StringBuilder sb = new StringBuilder();
        for (Transacao transacao : transacoes) {
            if (transacao instanceof Despesa && transacao.getCategoria().equals(categoria)) {
                sb.append(transacao.toString()).append("\n");
            }
        }
        txtArea.setText(sb.toString());
    
    
 // Adicione botões para consultar o saldo atual e total
    JButton btnSaldoAtual = new JButton("Consultar Saldo Atual");
    btnSaldoAtual.setBounds(20, 330, 170, 30);
    btnSaldoAtual.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            consultarSaldoAtual();
        }
    });
    frame.add(btnSaldoAtual);

    JButton btnSaldoTotal = new JButton("Consultar Saldo Total");
    btnSaldoTotal.setBounds(200, 330, 170, 30);
    btnSaldoTotal.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            consultarSaldoTotal();
        }
    });
    frame.add(btnSaldoTotal);

}
}


