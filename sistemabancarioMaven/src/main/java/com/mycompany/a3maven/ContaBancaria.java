package com.mycompany.a3maven;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import com.mycompany.a3maven.modelo.ContaBancariaDAO;

public class ContaBancaria {
    
    private Cliente cliente;
    private double saldo;
    private List<Movimentacao> movimentacoes;
    private List<Movimentacao> pix;

    public ContaBancaria(Cliente cliente) {
        cliente.setConta(true);
        this.cliente = cliente;
        this.saldo = 0;
        this.movimentacoes = new ArrayList<>();
        this.pix = new ArrayList<>();
    }
    
    public void depositar(double deposito) {
        LocalTime hora = LocalTime.now().withNano(0);
        this.saldo += deposito;
        movimentacoes.add(new Movimentacao("Depósito", deposito, hora));
        ContaBancariaDAO dao = new ContaBancariaDAO();
        dao.deposito(this.cliente.getCpf(), deposito);
    }
    
    public void sacar(double saque) {
        LocalTime hora = LocalTime.now().withNano(0);
        if (this.saldo >= saque){
            this.saldo -= saque;
            movimentacoes.add(new Movimentacao("Saque", saque, hora));
            ContaBancariaDAO dao = new ContaBancariaDAO();
            dao.saque(this.cliente.getCpf(), saque);
        } else {
            System.out.println("Saldo indisponível");
        }
    }
    
    /**
     * Transfer?ncia Pix entre contas.
     * @param cb
     * @param valor 
     */
    public void fazerPix(ContaBancaria cb, double valor) {
        
        LocalTime hora = LocalTime.now().withNano(0);
        LocalTime meianoite = LocalTime.MIDNIGHT;
        LocalTime quatroam = LocalTime.of(4, 0);
        LocalTime x = LocalTime.now();
        
        if(this.pix.size() >= 3){
            if(valor == this.pix.get(this.pix.size() - 1).getValor() 
            && valor == this.pix.get(this.pix.size() - 2).getValor()
            && valor == this.pix.get(this.pix.size() - 3).getValor()){
                System.err.println("\nComportamento suspeito - repetido de valores. Transação não realizada.\n");
            }
        } else if (valor > 2000) {
            System.err.println("\nComportamento suspeito - valor alto. Transação não realizada.\n");
        } else if (hora.isAfter(meianoite) || hora.equals(meianoite) && hora.isBefore(quatroam)) {
            System.err.println("\nComportamento suspeito - horário incomum.\n");
            this.saldo -= valor;
            cb.saldo += valor;
            pix.add(new Movimentacao(this.cliente.getNome(), cb.cliente.getNome(), "Transferência Pix", valor, hora));
            movimentacoes.add(new Movimentacao(this.cliente.getNome(), cb.cliente.getNome(), "Transferência Pix", valor, hora));
            ContaBancariaDAO dao = new ContaBancariaDAO();
            dao.pix(this.cliente.getCpf(), cb.cliente.getCpf(), valor);
        } else if (!this.cliente.getCpf().equals(cb.cliente.getCpf()) && this.saldo >= valor) {
            this.saldo -= valor;
            cb.saldo += valor;
            pix.add(new Movimentacao(this.cliente.getNome(), cb.cliente.getNome(), "Transferência Pix", valor, hora));
            movimentacoes.add(new Movimentacao(this.cliente.getNome(), cb.cliente.getNome(), "Transferência Pix", valor, hora));
            ContaBancariaDAO dao = new ContaBancariaDAO();
            dao.pix(this.cliente.getCpf(), cb.cliente.getCpf(), valor);
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public List<Movimentacao> getPix() {
        return pix;
    }

    public void setPix(List<Movimentacao> pix) {
        this.pix = pix;
    }
    
}
