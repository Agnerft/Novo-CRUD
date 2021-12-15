/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fcompany.crudpadrao.rn;

import br.com.fcompany.crudpadrao.controle.Controle;
import br.com.fcompany.crudpadrao.domain.Produto;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author agner
 */
public class RegraDeNegocio {

    Produto produto = new Produto();
    

    public void salvarProduto(Object... valor) throws SQLException {
        Controle controle = new Controle();

        System.out.println("1 E aqui?");
        produto.setNome((String) valor[0]);
        controle.insereProduto(produto);
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

    public void atualizarProduto(Object... valor) throws SQLException {

        Controle controle = new Controle();

        if ("".equals(valor[0])) {
            produto.setId(1);
        } else {
            produto.setId(Integer.valueOf((String) valor[0]));
        }
        produto.setNome(String.valueOf((String) valor[1]));

        controle.updateProduto(produto.getId(), produto.getNome());
    }

    public List<Produto> carregarListaDoBanco() throws SQLException {
        Controle controle = new Controle();
        List listaProduto = new LinkedList();

        controle.carregarLista();

        return listaProduto;

    }

}
