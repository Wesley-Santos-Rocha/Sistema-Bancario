package com.mycompany.sistemabancariomaven.modelo;

import java.sql.*;

public class ContaBancariaDAO {
    
    private static final String URL = "jdbc:mysql://localhost/a3maven";
    private static final String USUARIO = "root"; 
    private static final String SENHA = "root";
    private Connection conn = null;
    
    public void conectar() {    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do Banco de Dados n�o localizado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
        }
    }
    
    public void desconectar() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Desconectado");
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao desconectar: " + e.getMessage());
        }
    }
    
    public void deposito(String cpfconta, double valor) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado");
            String sqlInsert = "INSERT INTO Movimentacoes(cpfconta, valor, tipo) VALUES (?, ?, ?);";
            PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
            stmtInsert.setString(1, cpfconta);
            stmtInsert.setDouble(2, valor);
            stmtInsert.setString(3, "Dep�sito");
            stmtInsert.executeUpdate();
            stmtInsert.close();
            String sqlUpdate = "UPDATE Contas SET saldo = saldo + ? WHERE cpf = ?;";
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setDouble(1, valor);
            stmtUpdate.setString(2, cpfconta);
            stmtUpdate.executeUpdate();
            stmtUpdate.close();
            conn.close();
            System.out.println("Dep�sito realizado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do Banco de Dados n�o localizado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
        }
    }
    
    public void saque(String cpfconta, double valor) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado");
            String sqlInsert = "INSERT INTO Movimentacoes(cpfconta, valor, tipo) VALUES (?, ?, ?);";
            PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
            stmtInsert.setString(1, cpfconta);
            stmtInsert.setDouble(2, valor);
            stmtInsert.setString(3, "Saque");
            stmtInsert.executeUpdate();
            stmtInsert.close();
            System.out.println("Movimenta��o inserida no banco de dados");
            String sqlUpdate = "UPDATE Contas SET saldo = saldo - ? WHERE cpf = ?;";
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setDouble(1, valor);
            stmtUpdate.setString(2, cpfconta);
            stmtUpdate.executeUpdate();
            stmtUpdate.close();
            conn.close();
            System.out.println("Saque realizado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do Banco de Dados n�o localizado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
        }
    }
    
    public void pix(String cpfconta, String cpfdestinatario, double valor) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            String sqlInsert = "INSERT INTO Movimentacoes(cpfconta, cpfdestinatario, valor, tipo) VALUES (?, ?, ?, ?);";
            PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
            stmtInsert.setString(1, cpfconta);
            stmtInsert.setString(2, cpfdestinatario);
            stmtInsert.setDouble(3, valor);
            stmtInsert.setString(4, "Pix");
            stmtInsert.executeUpdate();
            stmtInsert.close();
            String sqlUpdate1 = "UPDATE Contas SET saldo = saldo - ? WHERE cpf = ?;";
            PreparedStatement stmtUpdate1 = conn.prepareStatement(sqlUpdate1);
            stmtUpdate1.setDouble(1, valor);
            stmtUpdate1.setString(2, cpfconta);
            stmtUpdate1.executeUpdate();
            stmtUpdate1.close();
            String sqlUpdate2 = "UPDATE Contas SET saldo = saldo + ? WHERE cpf = ?;";
            PreparedStatement stmtUpdate2 = conn.prepareStatement(sqlUpdate2);
            stmtUpdate2.setDouble(1, valor);
            stmtUpdate2.setString(2, cpfdestinatario);
            stmtUpdate2.executeUpdate();
            stmtUpdate2.close();
            conn.close();
            System.out.println("Transa��o realizada com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do Banco de Dados n�o localizado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
        }
    }

    public void resetarContas(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado");
            String sqlFK1 = "ALTER TABLE Movimentacoes DROP CONSTRAINT movimentacoes_ibfk_1;";
            PreparedStatement stmtFK1 = conn.prepareStatement(sqlFK1);
            stmtFK1.executeUpdate();
            stmtFK1.close();
            String sqlFK2 = "ALTER TABLE Movimentacoes DROP CONSTRAINT movimentacoes_ibfk_2";
            PreparedStatement stmtFK2 = conn.prepareStatement(sqlFK2);
            stmtFK2.executeUpdate();
            stmtFK2.close();
            String sqlTruncate = "TRUNCATE TABLE Contas;";
            PreparedStatement stmtTruncate = conn.prepareStatement(sqlTruncate);
            stmtTruncate.executeUpdate();
            stmtTruncate.close();
            conn.close();
            System.out.println("Tabela Contas resetada!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do Banco de Dados n�o localizado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco/n�o h� constraints: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        try {
            String sqlTruncate = "TRUNCATE TABLE Contas;";
            PreparedStatement stmtTruncate = conn.prepareStatement(sqlTruncate);
            stmtTruncate.executeUpdate();
            stmtTruncate.close();
            conn.close();
            System.out.println("Tabela Contas resetada!");
        } catch (SQLException e) {
            System.out.println("Erro ao truncar tabela: " + e.getMessage());
        }
    }
    
    public void resetarMovimentacoes() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado");
            String sqlFK1 = "ALTER TABLE Movimentacoes DROP CONSTRAINT movimentacoes_ibfk_1;";
            PreparedStatement stmtFK1 = conn.prepareStatement(sqlFK1);
            stmtFK1.executeUpdate();
            stmtFK1.close();
            String sqlFK2 = "ALTER TABLE Movimentacoes DROP CONSTRAINT movimentacoes_ibfk_2";
            PreparedStatement stmtFK2 = conn.prepareStatement(sqlFK2);
            stmtFK2.executeUpdate();
            stmtFK2.close();
            String sqlTruncate = "TRUNCATE TABLE Movimentacoes;";
            PreparedStatement stmtTruncate = conn.prepareStatement(sqlTruncate);
            stmtTruncate.executeUpdate();
            stmtTruncate.close();
            conn.close();
            System.out.println("Tabela Movimenta��es resetada!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do Banco de Dados n�o localizado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco/n�o h� constraints: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        try {
            String sqlTruncate = "TRUNCATE TABLE Movimentacoes;";
            PreparedStatement stmtTruncate = conn.prepareStatement(sqlTruncate);
            stmtTruncate.executeUpdate();
            stmtTruncate.close();
            conn.close();
            System.out.println("Tabela Movimenta��es resetada!");
        } catch (SQLException e) {
            System.out.println("Erro ao truncar tabela: " + e.getMessage());
        }
    }
    
}
