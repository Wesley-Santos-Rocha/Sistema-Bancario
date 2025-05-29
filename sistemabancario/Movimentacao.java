package sistemabancario;

public class Movimentacao {
    
    private String remetente;
    private String destinatario;
    private String tipo;
    private double valor;

    public Movimentacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public Movimentacao(String remetente, String destinatario, String tipo, double valor) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.tipo = tipo;
        this.valor = valor;
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

    @Override
    public String toString() {
        
        if (this.tipo == "Depósito" || this.tipo == "Saque") {
            return this.tipo + ": R$" + this.valor; 
        } else {
            return this.tipo + ": R$" + this.valor + "\nRemetente: " + 
                this.remetente + "\nDestinatário: " + this.destinatario; 
        }
        
    }
    
}
