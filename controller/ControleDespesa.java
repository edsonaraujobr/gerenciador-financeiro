package controller;

import java.util.ArrayList;
import data.DespesaDados;
import model.Despesa;

public class ControleDespesa {
    private DespesaDados despesaDados = new DespesaDados();
    
    public void adicionarDespesa (String nome,double valor) throws Exception {
        Despesa despesa = new Despesa (nome,valor);
        despesaDados.cadastrarDespesa(despesa);
    }
    
    public void adicionarDespesa (Despesa despesa) throws Exception {
        despesaDados.cadastrarDespesa(despesa);
    }
    
    public Despesa pesquisarDespesa(String nome) throws  Exception  { 
        return despesaDados.readDespesa(nome);
    }
    
    public ArrayList<Despesa> listarDespesas() throws Exception   {
        return despesaDados.listarDespesas();
    }
    
    public String imprimirDespesa() throws Exception{
        String retornar = "";
        ArrayList<Despesa> rec = this.listarDespesas();
        for (int c = 0; c < rec.size();c++) {
            retornar += rec.get(c).toString() + "\n __________________ \n"; // FAZER O IMPRIMIR
        }
        return retornar;
    }
    
    public void excluirDespesa (Despesa despesa) throws Exception  {
        despesaDados.excluirDespesa(despesa);
    }
    
    public void atualizarDespesa (Despesa despesa) throws Exception  {
        despesaDados.updateDespesa(despesa);
    }

}
