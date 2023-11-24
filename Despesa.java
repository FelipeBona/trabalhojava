/**
* Classe que representa uma despesa financeira.
* É usada para representar qualquer tipo de saída de dinheiro, como pagamentos ou compras.
* Estende a classe {@link Transacao}.
* 
* @see Transacao
*/

public class Despesa extends Transacao {
	
	/**
     * Constrói uma despesa com a categoria, valor e data especificados.
     * 
     * @param categoria a categoria da despesa
     * @param valor o valor monetário da despesa
     * @param data a data da despesa no formato dd/MM/yyyy
     */

    public Despesa(String categoria, double valor, String data) {
        super(categoria, valor, data);
    }
    
    /**
     * Calcula o impacto da despesa no saldo total.
     * 
     * @return o valor negativo da despesa
     */
 
    @Override
    public double impactoNoSaldo() {
        return -valor;
    }
}