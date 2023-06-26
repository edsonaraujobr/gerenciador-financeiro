package data;

import model.Receita;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReceitaDados {

    private String nomeArquivo = "Receita.ser";
    
    public ArrayList<Receita> listarReceitas () throws Exception { 
        ArrayList<Receita> receitas = new ArrayList();
        File arquivoReceita = new File (nomeArquivo);
        if (!arquivoReceita.exists()){
            arquivoReceita.createNewFile();
            return receitas;
        }
        FileInputStream fis;
        ObjectInputStream filmar = null;
        try {
            fis = new FileInputStream (arquivoReceita);
            filmar = new ObjectInputStream (fis);
            receitas = (ArrayList<Receita>)filmar.readObject();
            filmar.close();    
        }
        catch (FileNotFoundException ex) {
            System.out.println("Erro ao cadastrar receita");
        }
        catch (IOException ex) {
            System.out.println("Erro ao cadastrar receita");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Erro ao cadastrar receita");
        }
        return receitas;
    }
    
    public void cadastrarReceita (Receita receita) throws Exception  { 
        ArrayList <Receita> receitas = (ArrayList<Receita>) listarReceitas();
        receitas.add(receita);
        try {
            FileOutputStream fos = new FileOutputStream (nomeArquivo);
            ObjectOutputStream gravar = new ObjectOutputStream (fos);
            gravar.writeObject(receitas);
            gravar.close();  
        }
        catch (FileNotFoundException ex) {
            System.out.println("Erro ao cadastrar receita");
        }
        catch (IOException ex) {
            System.out.println("Erro ao cadastrar receita");
        }
    } 
    
    public Receita readReceita (String nome) throws Exception{ 
        ArrayList <Receita> receitas = (ArrayList<Receita>)this.listarReceitas();
        Receita receita = null;
        for (int c = 0; c < receitas.size();c++) {
            if (nome.equals(receitas.get(c).getNome())) { 
                receita = receitas.get(c);
                break;
            }
        }
        return receita;
    } 
    
    public void excluirReceita(Receita receita) throws Exception { 
        ArrayList <Receita> receitas = (ArrayList<Receita>)this.listarReceitas();
        boolean achou = false;
        for (int c = 0; c < receitas.size();c++) {
            if (receita.getNome().equals(receitas.get(c).getNome())) { 
                receitas.remove(c);
                achou = true;
                break;
            }
        }
        if (achou) {
            atualizarArquivo(receitas);
        }
    }

    public void atualizarArquivo(ArrayList <Receita> receitas) throws Exception{ 
        try {
            FileOutputStream fos = new FileOutputStream (nomeArquivo);    
            ObjectOutputStream gravar = new ObjectOutputStream (fos);
            gravar.writeObject(receitas);   
            gravar.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Erro ao cadastrar receita");
        }
        catch (IOException ex) {
            System.out.println("Erro ao cadastrar receita");
        }
    }
    
    public void updateReceita(Receita receita) throws Exception { 
        ArrayList <Receita> receitas = (ArrayList<Receita>)this.listarReceitas();
        boolean achou = false;
        for (int c = 0; c < receitas.size();c++) {
            if (receita.getData().equals(receitas.get(c).getData())) { 
                receitas.remove(c);
                receitas.add(receita);
                achou = true;
                break;
            }
        }
        if (achou) {
            atualizarArquivo(receitas);
        }
    }
}
