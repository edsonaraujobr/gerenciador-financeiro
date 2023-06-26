package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Receita;

public class TelaAdicionarReceitaController implements Initializable {

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private AnchorPane anchorPaneTelaAdicionarReceita;
    
    @FXML
    private Button buttonAtualizar;

    @FXML
    private Label labelStatusCadastroSalgado;

    @FXML
    private TextField textFieldNomeReceita;

    @FXML
    private TextField textFieldValorReceita;
       
    @FXML
    private Text textCamposVazios;
    
    private ControleReceita controleReceita = new ControleReceita();
    
    private Receita receita;
    
    private Stage dialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        limparCampos();
    }

    @FXML
    void handleButtonCancelar(ActionEvent event) {
        this.dialogStage.close();
    }


    @FXML
    void handleButtonConfirmar(ActionEvent event) {
        if (!textFieldNomeReceita.getText().equals("") && (!textFieldValorReceita.getText().equals(""))) {
            
            String nome = textFieldNomeReceita.getText().toUpperCase();
            double valor = Double.parseDouble(textFieldValorReceita.getText());
            this.receita=new Receita(nome,  valor);
            
            this.dialogStage.close();
        } else {
            textCamposVazios.setVisible(true);
            textFieldNomeReceita.requestFocus();
        }
    }
    
    private void limparCampos(){     
       textFieldNomeReceita.clear();
       textFieldValorReceita.clear();   
       textFieldNomeReceita.requestFocus();
    }
    
    public Receita getReceita() {
        return receita;
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        dialogStage.setResizable(false);
    }
    
    public Stage getDialogStage() {
        return dialogStage;
    } 
}
