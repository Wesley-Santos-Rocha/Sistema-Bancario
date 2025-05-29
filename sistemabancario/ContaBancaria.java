package sistemabancario;

import java.util.List;
import java.util.ArrayList;

public class ContaBancaria {
    
    private Cliente cliente;
    private double saldo;
    private List<Movimentacao> movimentacoes;
    private List<Movimentacao> pix;

    public ContaBancaria(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0;
        this.movimentacoes = new ArrayList<>();
        this.pix = new ArrayList<>();
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

    public void fazerPix(ContaBancaria cb, double valor) {
        if(this.pix.size() >= 3 && (valor == this.pix.get(this.pix.size() - 1).getValor() && valor == this.pix.get(this.pix.size() - 2).getValor()
             && valor == this.pix.get(this.pix.size() - 3).getValor())) {
            System.err.println("\nComportamento suspeito. Transação não realizada.\n");
        } else if (this.cliente.getCpf() != cb.cliente.getCpf()) {
            this.saldo -= valor;
            cb.saldo += valor;
            pix.add(new Movimentacao(this.cliente.getNome(), cb.cliente.getNome(), "Transferência Pix", valor));
            movimentacoes.add(new Movimentacao(this.cliente.getNome(), cb.cliente.getNome(), "Transferência Pix", valor));
        }
    }
    
    public void imprimirExtrato() {
        System.out.println("===EXTRATO===");
        System.out.println("Extrato da conta de " + this.cliente.getNome());
        for(Movimentacao m : movimentacoes) {
            System.out.println(m + "\n");
        }
        System.out.println("Saldo: " + this.saldo);
    }
    
}
