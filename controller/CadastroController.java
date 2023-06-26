package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Usuario;

public class CadastroController implements Initializable {
      
    @FXML
    private AnchorPane anchorPaneTelaToda;

    @FXML
    private PasswordField textFieldRepetirSenha;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private TextField textFieldNome;

    @FXML
    private PasswordField textFieldSenha;

    @FXML
    private Label labelSenhasNaoConferem;
    
    @FXML
    private Label labelCamposVazios;
      
    @FXML
    private Button buttonVoltar;
    
    @FXML
    private Label labelNomeJaExiste;
    
    private ControleUsuario controleUsuario = new ControleUsuario();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void handleButtonConfirmar(ActionEvent event) throws Exception{
        if ((!textFieldNome.getText().equals("")) && (!textFieldSenha.getText().equals("")) && (!textFieldRepetirSenha.getText().equals(""))) {
            if((textFieldSenha.getText().equals(textFieldRepetirSenha.getText()))){
                ArrayList<Usuario> usuarios = (ArrayList<Usuario>)controleUsuario.listarUsuarios();
                boolean entrou = false;
                for (int c = 0; c < usuarios.size();c++) {
                    if (textFieldNome.getText().equals(usuarios.get(c).getNome())) {
                        labelNomeJaExiste.setVisible(true);
                        entrou = true;
                        break;
                    }
                }
                if (entrou == false) {
                    Usuario usuario = new Usuario(textFieldNome.getText(),textFieldSenha.getText());
                    controleUsuario.cadastrarUsuario(textFieldNome.getText(),textFieldSenha.getText());
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
                        anchorPaneTelaToda.getChildren().setAll(a);
                    } catch (IOException ex) {
                        Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else{
                labelCamposVazios.setVisible(false);
                labelSenhasNaoConferem.setVisible(true);
                textFieldSenha.requestFocus();
            }
        } else {
            labelSenhasNaoConferem.setVisible(false);
            labelCamposVazios.setVisible(true);
            textFieldNome.requestFocus();
        }  
    }
    
    @FXML
    void handleButtonVoltar(ActionEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/Login.fxml"));
            anchorPaneTelaToda.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
