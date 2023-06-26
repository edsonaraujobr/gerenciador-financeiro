package controller;

import model.Usuario;
import data.UsuarioDados;
import java.io.IOException;
import java.util.ArrayList;
 
public class ControleUsuario {
    private UsuarioDados usuarioDados = new UsuarioDados();
    
    public void cadastrarUsuario (String nome,String senha) throws Exception{
        Usuario usuario = new Usuario (nome,senha);
        usuarioDados.cadastrarUsuario(usuario);
    }
    
    public Usuario pesquisarUsuario (String nome, String senha) throws IOException, Exception {
        return usuarioDados.pesquisarUsuario(nome,senha);
    }
    
    public ArrayList<Usuario> listarUsuarios() throws Exception {
        return usuarioDados.listarUsuarios();
    }
    
    
}
