/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fcompany.crudpadrao.controle;

import br.com.fcompany.crudpadrao.domain.Produto;
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

    public void openConnection() throws SQLException {

        System.out.println("5 Conectou?");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cliente", "postgres", "postgres");
    }

    public void salvarProduto(Object... valor) throws SQLException {
        Controle controle = new Controle();
//        Produto produto = new Produto();
        System.out.println("1 E aqui?");

        produto.setNome((String) valor[0]);

        controle.insereProduto(produto);

    }

    public void insereProduto(Produto produto) throws SQLException {
        openConnection();

        System.out.println("2 Passsou aqui?");
        String SQL = "INSERT INTO novoTeste (nome) VALUES (?) RETURNING id";
        pdst = con.prepareStatement(SQL);

        pdst.setObject(1, produto.getNome());
        System.out.println("3 Teste");
        result = pdst.executeQuery();

    }

    public void selecionarPorId(Object... valor) throws SQLException {
        Controle controle = new Controle();
        //Produto produto = new Produto();
        System.out.println("SELECT POR ID");
        if ("".equals(valor[0])) {
            produto.setId(1);
        } else {
            produto.setId(Integer.valueOf((String) valor[0]));
        }

        controle.deletaProduto(produto.getId());

    }

    public void deletaProduto(int idProduto) throws SQLException {

        openConnection();

        String SQL = "DELETE from novoTeste where id = ?";
        System.out.println("Teste de delete");
        pdst = con.prepareStatement(SQL);
        pdst.setInt(1, idProduto);
        pdst.execute();
    }

    public void updateProduto(Produto produto, int idProduto) throws SQLException {
        openConnection();
        String SQL = "UPDATE novoTeste set nome=? where id=?";
        pdst = con.prepareStatement(SQL);

        pdst.setInt(2, idProduto);
        pdst.setString(1, produto.getNome());
        pdst.execute();
    }

    public List<Produto> carregarListaDoBanco() throws SQLException {
        Controle controle = new Controle();
        List listaProduto = new LinkedList();

        controle.carregarLista();

        return listaProduto;

    }

    public List<Produto> carregarLista() throws SQLException {
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

    public void consultarProduto() throws SQLException {
        openConnection();

    }

    public void closeConnection() throws SQLException {
        con.close();
    }
}
