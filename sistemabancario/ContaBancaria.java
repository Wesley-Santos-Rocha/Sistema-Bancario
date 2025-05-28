package sistemabancario;

import java.util.List;
import java.util.ArrayList;

public class ContaBancaria {
    
    private Cliente cliente;
    private double saldo;
    private List<Movimentacao> movimentacoes;

    public ContaBancaria(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0;
        this.movimentacoes = new ArrayList<>();
    }
    
    public void depositar(double deposito) {
        this.saldo += deposito;
        movimentacoes.add(new Movimentacao("Depósito", deposito));
    }
    
    public void sacar(double saque) {
        if (this.saldo >= saque){
            this.saldo -= saque;
            movimentacoes.add(new Movimentacao("Saque", saque));
        }
    }
    
    public void imprimirExtrato() {
        System.out.println("Extrato da conta de " + this.cliente.getNome());
        for(Movimentacao m : movimentacoes) {
            System.out.println(m);
        }
        System.out.println("Saldo: " + this.saldo);
    }
    
}
