/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fcompany.crudpadrao.utilidades;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author agner
 */
public class Tabela {

    public JTable criarTabela(JPanel jpn, Object[] largura, Object[] pos, Object[] col){
        JTable tabela = new JTable(new DefaultTableModel());
        
        tabela.setVisible(true);
        JScrollPane jsp = new JScrollPane(tabela);
        jsp.setBounds(10, 30, 500, 130);
        jsp.setVisible(true);
        jpn.add(jsp);
        
        DefaultTableModel modeloTabela = (DefaultTableModel) tabela.getModel();
        
        for (int i = 0; i < col.length; i++) {
            modeloTabela.addColumn(col[i]);
        }
        
        return tabela;

    }
    
}
