package sistemabancario;

public class Main {
    
    public static void main(String[] args) {
        
        Cliente c1 = new Cliente("Adhemar", "01234567890");
        Cliente c2 = new Cliente("AIlton", "09876543210");
        
        ContaBancaria cb1 = new ContaBancaria(c1);
        ContaBancaria cb2 = new ContaBancaria(c2);
        
        cb1.depositar(100);
        cb1.sacar(10);
        
        cb1.imprimirExtrato();
        
        cb1.fazerPix(cb2, 10);
        
        cb1.imprimirExtrato();
        
    }
    
}
