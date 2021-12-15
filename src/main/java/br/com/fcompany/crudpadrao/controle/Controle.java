/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fcompany.crudpadrao.controle;

import br.com.fcompany.crudpadrao.domain.Produto;
import br.com.fcompany.crudpadrao.rn.RegraDeNegocio;
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
    RegraDeNegocio regra = new RegraDeNegocio();

    public void openConnection() throws SQLException {

    System.out.println("5 Conectou?");
    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cliente", "postgres", "postgres");
    }

    public void insereProduto(Produto produto) throws SQLException { // CONECTA NO BANCO
        openConnection();
        
        System.out.println("2 Passsou aqui?");
        String SQL = "INSERT INTO novoTeste (nome) VALUES (?) RETURNING id";
        pdst = con.prepareStatement(SQL);

        pdst.setObject(1, produto.getNome());
        System.out.println("3 Teste");
        result = pdst.executeQuery();

    }

    public void deletaProduto(int idProduto) throws SQLException { // CONECTA NO BANCO

        openConnection();

        String SQL = "DELETE from novoTeste where id = ?";
        System.out.println("Teste de delete");
        pdst = con.prepareStatement(SQL);
        pdst.setInt(1, idProduto);
        pdst.execute();
    }

    public void updateProduto(int idProduto, String nome) throws SQLException { // CONECTA NO BANCO
        openConnection();
        String SQL = "UPDATE novoTeste set nome=? where id=?";
        pdst = con.prepareStatement(SQL);
        pdst.setInt(2, idProduto);
        pdst.setString(1, nome);
        pdst.execute();
    }

    public List<Produto> carregarLista() throws SQLException { // CONECTA NO BANCO
        openConnection();

        List<Produto> produtos = new LinkedList<>();

        String SQL = "select * from novoTeste";

        pdst = con.prepareStatement(SQL);
        result = pdst.executeQuery();

        while (result.next()) {
            Produto produto = new Produto();
            produto.setId(result.getInt("id"));
            produto.setNome(result.getString("nome"));

            produtos.add(produto);

        }

        return produtos;
    }

    public void closeConnection() throws SQLException {
        con.close();
    }
}
