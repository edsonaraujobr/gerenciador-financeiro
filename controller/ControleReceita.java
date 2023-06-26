package controller;

import java.util.ArrayList;
import data.ReceitaDados;
import model.Receita;

public class ControleReceita {
    private ReceitaDados receitaDados = new ReceitaDados();
    
    public void adicionarReceita (String nome, double valor) throws Exception {
        Receita receita = new Receita (nome,valor);
        receitaDados.cadastrarReceita(receita);
    }
    
    public void adicionarReceita (Receita receita) throws Exception {
        receitaDados.cadastrarReceita(receita);
    }

    public Receita pesquisarReceita(String nome) throws Exception  { 
        return receitaDados.readReceita(nome);
    }
    
    public ArrayList<Receita> listarReceitas() throws Exception {
        return receitaDados.listarReceitas();
    }
    
    public String imprimirReceita() throws Exception{
        String retornar = "";
        ArrayList<Receita> rec = this.listarReceitas();
        for (int c = 0; c < rec.size();c++) {
            retornar += rec.get(c).toString() + "\n __________________ \n"; // FAZER O IMPRIMIR
        }
        return retornar;
    }
    
    public void excluirReceita (Receita receita) throws Exception {
        receitaDados.excluirReceita(receita);
    }
    
    public void atualizarReceita (Receita receita) throws Exception {
        receitaDados.updateReceita(receita);
    }

}
