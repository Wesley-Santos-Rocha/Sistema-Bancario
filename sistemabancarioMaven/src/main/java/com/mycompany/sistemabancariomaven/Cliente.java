package com.mycompany.sistemabancariomaven;

import com.mycompany.sistemabancariomaven.modelo.ClienteDAO;

public class Cliente {
    
    private String nome;
    private String cpf;
    private boolean conta;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.conta = false;
        
    }
    
    public void criarCliente() {
        ClienteDAO dao = new ClienteDAO();
        dao.inserirCliente(cpf, nome, 0);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean getConta() {
        return conta;
    }

    public void setConta(boolean conta) {
        this.conta = conta;
    }
    
}
