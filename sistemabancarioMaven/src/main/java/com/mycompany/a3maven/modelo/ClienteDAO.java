package com.mycompany.a3maven.modelo;

import java.sql.*;

public class ClienteDAO {
    
    private static final String URL = "jdbc:mysql://localhost/a3maven";
    private static final String USUARIO = "root"; 
    private static final String SENHA = "root";
    private Connection conn = null;
    
    public void inserirCliente(String cpf, String nome, double saldo) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado");
            String sql = "INSERT INTO Contas(cpf, nome, saldo) VALUES (?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setDouble(3, saldo);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            if (conn != null) {
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do Banco de Dados não localizado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
        }
    }
    
}
