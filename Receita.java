/**
* Classe que representa uma receita financeira.
* É usada para representar qualquer tipo de entrada de dinheiro, como salário ou vendas.
* Estende a classe {@link Transacao}.
* 
* @see Transacao
*/
public class Receita extends Transacao {
    /**
     * Constrói uma receita com a categoria, valor e data especificados.
     * 
     * @param categoria a categoria da receita
     * @param valor o valor monetário da receita
     * @param data a data da receita no formato dd/MM/yyyy
     */
    public Receita(String categoria, double valor, String data) {
        super(categoria, valor, data);
    }
 
    /**
     * Calcula o impacto da receita no saldo total.
     * 
     * @return o valor da receita
     */
    
    @Override
    public double impactoNoSaldo() {
        return valor;
    }
}