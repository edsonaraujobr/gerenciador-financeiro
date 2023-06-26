package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nome;
    private String senha;
    
    public Usuario (String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
    
    public Usuario () {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 
    
    public String getSenha(){
        return senha;
    }
}
