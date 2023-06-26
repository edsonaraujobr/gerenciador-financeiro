package data;

import model.Despesa;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DespesaDados {
    
    private String nomeArquivo = "Despesa.ser";

    public ArrayList<Despesa> listarDespesas () throws Exception { 
        ArrayList<Despesa> despesas = new ArrayList();
        File arquivoDespesa = new File (nomeArquivo);
        if (!arquivoDespesa.exists()){
            arquivoDespesa.createNewFile();
            return despesas;
        }
        FileInputStream fis;
        ObjectInputStream filmar = null;
        try {
            fis = new FileInputStream (arquivoDespesa);
            filmar = new ObjectInputStream (fis);
            despesas = (ArrayList<Despesa>)filmar.readObject();
            filmar.close();  
        }
        catch (FileNotFoundException ex) {
            System.out.println("Erro ao listar despesas");
        }
        catch (IOException ex) {
            System.out.println("Erro ao listar despesas");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Erro ao listar despesas");
        }
        return despesas;
    } 
    
    public void cadastrarDespesa (Despesa despesa) throws Exception  { 
        ArrayList <Despesa> despesas = (ArrayList<Despesa>) listarDespesas();
        despesas.add(despesa);
        try {
            FileOutputStream fos = new FileOutputStream (nomeArquivo);
            ObjectOutputStream gravar = new ObjectOutputStream (fos);
            gravar.writeObject(despesas);
            gravar.close();  
        }
        catch (FileNotFoundException ex) {
            System.out.println("Erro ao cadastrar despesa");
        }
        catch (IOException ex) {
            System.out.println("Erro ao cadastrar despesa");
        }
    }     
    
    public Despesa readDespesa (String nome) throws Exception{ 
        ArrayList <Despesa> despesas = (ArrayList<Despesa>) this.listarDespesas();
        Despesa despesa = null;
        for (int c = 0; c < despesas.size();c++) {
            if (nome.equals(despesas.get(c).getNome())) { 
                despesa = despesas.get(c);
                break;
            }
        }
        return despesa;
    } 

    public void excluirDespesa(Despesa despesa) throws Exception { 
        ArrayList <Despesa> despesas = (ArrayList<Despesa>) this.listarDespesas();
        boolean achou = false;
        for (int c = 0; c < despesas.size();c++) {
            if (despesa.getNome().equals(despesas.get(c).getNome())) { 
                despesas.remove(c);
                achou = true;
                break;
            }
        }
        if (achou) {
            atualizarArquivo(despesas);
        }
    }
    
    private void atualizarArquivo (ArrayList <Despesa> despesas) throws Exception{
        try{
            FileOutputStream fluxo = new FileOutputStream(nomeArquivo);
            ObjectOutputStream escreverObj = new ObjectOutputStream(fluxo);
            escreverObj.writeObject(despesas);
            escreverObj.close();   
        } catch (FileNotFoundException ex) {
            System.out.println ("Erro ao atualizar arquivo");
        } catch (IOException ex) {
            System.out.println ("Erro ao atualizar arquivo");
        }
    }
    
    public void updateDespesa(Despesa despesa) throws Exception { 
        ArrayList <Despesa> despesas = (ArrayList<Despesa>) this.listarDespesas();
        boolean achou = false;
        for (int c = 0; c < despesas.size();c++) {
            if (despesa.getData().equals(despesas.get(c).getData())) { 
                despesas.remove(c);
                despesas.add(despesa);
                achou = true;
                break;
            }
        }
        if (achou) {
            atualizarArquivo(despesas);
        }
    } 
 
}
