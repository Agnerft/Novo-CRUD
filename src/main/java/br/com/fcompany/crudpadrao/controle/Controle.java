/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fcompany.crudpadrao.controle;

import br.com.fcompany.crudpadrao.domain.Produto;
import br.com.fcompany.crudpadrao.form.Tela;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author agner
 */
public class Controle {
    
    Connection con;
    PreparedStatement pdst;
    ResultSet result;
    Produto produto = new Produto();
   
    
    public void openConnection() throws SQLException{
        
        System.out.println("Conectou?");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cliente", "postgres", "postgres");
    }
    
    public void insereProduto(Produto produto) throws SQLException{
        openConnection();
        
        String SQL = "INSERT INTO novoTeste (nome) VALUES (?) RETURNING id";
        pdst = con.prepareStatement(SQL);
        
        pdst.setString(1, produto.getNome());
        
        result = pdst.executeQuery();
   
    }
    
    public void deletaProduto(int idProduto) throws SQLException{
        
        openConnection();
        
        String SQL = "DELETE from novoTeste where id = ?";
        
        pdst = con.prepareStatement(SQL);
        pdst.setInt(1, idProduto);
        pdst.execute();
    }
    
    public void updateProduto(Produto produto, int idProduto) throws SQLException{
        openConnection();
        String SQL = "UPDATE novoTeste set nome=? where id=?";
        pdst = con.prepareStatement(SQL);
        
        
        pdst.setInt(2, idProduto);
        pdst.setString(1, produto.getNome());
        pdst.execute();      
    }
    
    
    public List<Produto> carregarLista() throws SQLException{
       openConnection();
        
        List<Produto> produtos = new LinkedList<>();
        
        String SQL = "select * from novoTeste";
        
        pdst = con.prepareStatement(SQL);
        result = pdst.executeQuery();
        
        while(result.next()){
           Produto produto = new Produto();
           produto.setId(result.getInt("id"));
           produto.setNome(result.getString("nome"));
           
           produtos.add(produto);
           
        
            System.out.println(produto);
        
    }
        
        return produtos;
    } 
    
    public void closeConnection() throws SQLException{
        con.close();
    }
}
//       public static void main(String[] args) throws SQLException {
//        
//         
//               
//        Produto produto = new Produto();
//        
//        produto.setNome("TESTE5");
//        
//        Controle controle = new Controle();
//        
//        //controle.insereProduto(produto);
//        
//        controle.deletaProduto(1);
//        
//        //controle.updateProduto(produto, 1);
//        
//        //controle.carregarLista();
//   }
//   
//    
//   
//}

