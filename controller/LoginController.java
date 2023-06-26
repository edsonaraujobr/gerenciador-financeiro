package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import model.Usuario;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    
    @FXML
    private TextField textFieldNome;
    
    @FXML
    private AnchorPane anchorPaneLogin;

    @FXML
    private Button buttonEntrar;
    
    @FXML
    private Label labelCadastrar;

    @FXML
    private PasswordField textFieldSenha;
    
    @FXML
    private ProgressIndicator pg_carregando;
      
    @FXML
    private Text text_logou;
    
    @FXML
    private Label labelNenhumUsuario;
    
    @FXML
    private Label labelCamposVazios;
    
    private ControleUsuario controleUsuario = new ControleUsuario();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void handleButtonEntrar(ActionEvent event) throws IOException, Exception {
        if (!textFieldNome.getText().equals("")&& !textFieldSenha.getText().equals("")) { 
            boolean entrou = false;
            ArrayList<Usuario> listUsuarios = (ArrayList<Usuario>)controleUsuario.listarUsuarios();
            for (int c = 0; c < listUsuarios.size(); c++) {
                if (textFieldNome.getText().equals(listUsuarios.get(c).getNome()) && textFieldSenha.getText().equals(listUsuarios.get(c).getSenha())) {
                    AnchorPane a;   
                    try {  
                        Stage stage = new Stage();
                        AnchorPane b = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/BemVindo.fxml"));
                        Scene scene = new Scene(b);
                        stage.setScene(scene);
                        stage.show();
                        PauseTransition pause = new PauseTransition(Duration.seconds(2));
                        pause.setOnFinished(eventx -> {
                            stage.close();
                        });
                        pause.play();
                        a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/TelaInicial.fxml"));
                        anchorPaneLogin.getChildren().setAll(a);
                    } catch (IOException ex) {
                        Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    entrou = true;
                    break;
                }
            }
            if (entrou == false) {
                labelCamposVazios.setVisible(false);
                labelNenhumUsuario.setVisible(true);
                textFieldNome.requestFocus();
            }
        } else {
            labelNenhumUsuario.setVisible(false);
            labelCamposVazios.setVisible(true);
            textFieldNome.requestFocus();
        }
    } 
    
    @FXML
    void handleButtonCadastrar(MouseEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/Cadastro.fxml"));
            anchorPaneLogin.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


