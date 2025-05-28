package sistemabancario;

public class Main {
    
    public static void main(String[] args) {
        
        Cliente c1 = new Cliente("Adhemar", "01234567890");
        
        ContaBancaria cb1 = new ContaBancaria(c1);
        
        cb1.depositar(100);
        cb1.sacar(10);
        
        cb1.imprimirExtrato();
        
    }
    
}
