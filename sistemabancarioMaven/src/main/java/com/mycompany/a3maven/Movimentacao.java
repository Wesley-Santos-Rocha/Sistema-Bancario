package com.mycompany.a3maven;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {
    
    private String remetente;
    private String destinatario;
    private String tipo;
    private double valor;
    private LocalTime hora;


    public Movimentacao(String tipo, double valor, LocalTime hora) {
        this.tipo = tipo;
        this.valor = valor;
        this.hora = hora;
    }

    public Movimentacao(String remetente, String destinatario, String tipo, double valor, LocalTime hora) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.tipo = tipo;
        this.valor = valor;
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalTime getHora() {
        return this.hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        
        LocalTime hora = LocalTime.now().withNano(0);
        String horaString = hora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        if (this.tipo == "Dep�sito" || this.tipo == "Saque") {
            return this.tipo + ": R$" + this.valor + " �s " + horaString; 
        } else {
            return this.tipo + ": R$" + this.valor  + " �s " + horaString + "\nRemetente: " + 
                this.remetente + "\nDestinat�rio: " + this.destinatario; 
        }
        
    }
    
}
