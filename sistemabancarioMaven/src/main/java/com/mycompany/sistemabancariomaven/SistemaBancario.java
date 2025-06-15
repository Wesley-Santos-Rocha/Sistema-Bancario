package com.mycompany.sistemabancariomaven;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mycompany.sistemabancariomaven.modelo.ContaBancariaDAO;

public class SistemaBancario {

    public static void main(String[] args) {

        /*
        Cliente c1 = new Cliente("Adhemar", "01234567890");
        Cliente c2 = new Cliente("AIlton", "09876543210");
        
        ContaBancaria cb1 = new ContaBancaria(c1);
        ContaBancaria cb2 = new ContaBancaria(c2);
        
        cb1.depositar(100);
        cb1.sacar(10);
        
        cb1.imprimirExtrato();
        
        cb1.fazerPix(cb2, 10);
        
        cb1.imprimirExtrato();
        */
        
        Scanner teclado = new Scanner(System.in);

        List<Cliente> lc = new ArrayList<>();
        List<ContaBancaria> lcb = new ArrayList<>();

        Cliente c1 = new Cliente("Cliente", "01234567890");
        c1.criarCliente();
        ContaBancaria cb1 = new ContaBancaria(c1);
        Cliente c2 = new Cliente("maria", "09876543210");
        c2.criarCliente();
        ContaBancaria maria = new ContaBancaria(c2);

        lc.add(c1);
        lc.add(c2);

        lcb.add(cb1);
        lcb.add(maria);

        int escolha;

        do {
            System.out.printf("---MENU DO %s---\n", c1.getNome());
            System.out.printf("Qual atividade você gostaria de realizar, %s?\n", c1.getNome());
            System.out.println("[1] - Sair\n[2] - Depositar\n[3] - Sacar\n[4] - Fazer Pix\n[5] - Imprimir Extrato");
            System.out.print("Escolha: ");
            escolha = teclado.nextInt();
            switch (escolha) {

                case 1:
                    ContaBancariaDAO dao = new ContaBancariaDAO();
                    dao.resetarContas();
                    dao.resetarMovimentacoes();
                    teclado.close();
                    System.exit(0);
                    break;
                case 2:
                    System.out.print("Valor do Depósito: ");
                    double valord = teclado.nextDouble();
                    cb1.depositar(valord);
                    break;
                case 3:
                    System.out.print("Valor do Saque: ");
                    double valors = teclado.nextDouble();
                    cb1.sacar(valors);
                    break;
                case 4:
                    teclado.nextLine();
                    System.out.println("---Contas Bancárias disponíveis---");
                    for(ContaBancaria cb : lcb) {
                        if(cb != cb1) {
                            System.out.printf("- %s\n", cb.getCliente().getNome());
                        }
                    }
                    System.out.println("----------------------------------");
                    System.out.print("Destinatário: ");
                    String cb = teclado.nextLine();
                    System.out.print("Valor do Pix: ");
                    double valorp = teclado.nextDouble();
                    switch (cb) {
                        case "maria":
                            cb1.fazerPix(maria, valorp);
                            break;
                            default:
                            System.out.println("Conta Indisponível");
                    }
                    break;
                case 5:
                    cb1.imprimirExtrato();
                    break;
                default:
                    System.out.println("Escolha indisponível");

            }
        } while(escolha != 1);
        
    }
}
