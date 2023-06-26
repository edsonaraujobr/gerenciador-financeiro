package data;

import model.Usuario;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class UsuarioDados {
    private String nomeArquivo = "Usuario.ser";
        public void cadastrarUsuario (Usuario usuario) throws Exception { 
        ArrayList <Usuario> usuarios = (ArrayList<Usuario>) listarUsuarios();
        usuarios.add(usuario);
        try {
            FileOutputStream fos = new FileOutputStream (nomeArquivo);
            ObjectOutputStream gravar = new ObjectOutputStream (fos);
            gravar.writeObject(usuarios);
            gravar.close();  
        }
        catch (FileNotFoundException ex) {
            System.out.println("Erro ao cadastrar usuario");
        }
        catch (IOException ex) {
            System.out.println("Erro ao cadastrar usuario");
        }
    } 
    
    public Usuario pesquisarUsuario (String nome, String senha) throws IOException, Exception{ 
        ArrayList <Usuario> usuarios =  this.listarUsuarios();
        Usuario usuario = null;
        for (int c = 0; c < usuarios.size();c++) {
            if (nome.equals(usuarios.get(c).getNome()) && senha.equals(usuarios.get(c).getSenha())) { 
                usuario = usuarios.get(c);
                break;
            }
        }
        return usuario;
    }
    
    public ArrayList<Usuario> listarUsuarios () throws Exception { 
        ArrayList<Usuario> usuarios = new ArrayList();
        File arquivoUsuario = new File (nomeArquivo);
        if (!arquivoUsuario.exists()){
            arquivoUsuario.createNewFile();
            return usuarios;
        }
        FileInputStream fis;
        ObjectInputStream filmar = null;
        try {
            fis = new FileInputStream (arquivoUsuario);
            filmar = new ObjectInputStream (fis);
            usuarios =  (ArrayList<Usuario>)filmar.readObject();
            filmar.close();    
        }
        catch (FileNotFoundException ex) {
            System.out.println("Erro ao listar usuarios");
        }
        catch (IOException ex) {
            System.out.println("Erro ao listar usuarios");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Erro ao listar usuarios");
        }
        return usuarios;
    }
    
}
