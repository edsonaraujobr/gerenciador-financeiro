package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Receita implements Serializable, Categoria {
    private Date data;
    private String nome;
    private double valor;
    
    public Receita (String nome, double valor) { // Construtor com data automática
        this.nome = nome;
        this.valor = valor;
        Calendar c = Calendar.getInstance();
        this.data = c.getTime();
    }
    
    public Receita (String nome, double valor, Date data) { // Construtor com data como parâmetro extra.
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public void setNome(String nome){
        this.nome = nome;
    }
    
    @Override
    public double getValor() {
        return valor;
    }
    
    @Override
    public void setValor(double valor) {
        this.valor = valor;
    } 
    
    @Override
    public String toString() {
        return "Nome: " + getNome() + "\nValor: " + getValor() + "\nData: " + getData();
    }
}
