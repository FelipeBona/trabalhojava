import java.text.DecimalFormat;

/**
 * Classe abstrata que representa uma transação financeira.
 * Esta classe é a base para todas as transações e define a estrutura comum
 * que todas as transações devem seguir.
 * 
 * @author Nicholas Morbeck Nigro , Felipe Bona,Nicolas Reiter
 */
 
public abstract class Transacao {

    protected String categoria;

    protected double valor;

    protected String data;
    
    /**
     * Constrói uma transação com a categoria, valor e data especificados.
     * 
     * @param categoria a categoria da transação
     * @param valor o valor monetário da transação
     * @param data a data da transação no formato dd/MM/yyyy
     */

 
    public Transacao(String categoria, double valor, String data) {

        this.categoria = categoria;

        this.valor = valor;

        this.data = data;

    }
 
    public String getCategoria() {

        return categoria;

    }
 
    public double getValor() {

        return valor;

    }
 
    public String getData() {

        return data;

    }
 
    // Método que retorna o impacto da transação no saldo.

 	

	public abstract double impactoNoSaldo();
 
    @Override
    
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return data + " - " + categoria + ": R$ " + df.format(valor);
    }

}
