package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Receita;

public class telaEditarReceitaController implements Initializable {
    
    @FXML
    private AnchorPane anchorPaneTelaEditarDespesa;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonSalvar;

    @FXML
    private Text textCamposVazios;

    @FXML
    private TextField textFieldNomeReceita;

    @FXML
    private TextField textFieldValorReceita;
    
    private Receita receita;
    
    private ControleDespesa controleDespesa = new ControleDespesa();
    
    private Stage dialogStage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        limparCampos();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        dialogStage.setResizable(false);
    }
    
    public Stage getDialogStage() {
        return dialogStage;
    }
    
    @FXML
    void handleButtonCancelar(ActionEvent event) {
        this.dialogStage.close();
    }

    @FXML
    void handleButtonSalvar(ActionEvent event) {
        if (!textFieldNomeReceita.getText().equals("") && (!textFieldValorReceita.getText().equals(""))) {
            this.receita.setNome(this.textFieldNomeReceita.getText().toUpperCase());
            this.receita.setValor(Double.parseDouble(this.textFieldValorReceita.getText())); 
            
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

    public void setReceita(Receita receita) {
        this.receita = receita;
        this.textFieldNomeReceita.setText(String.valueOf(receita.getNome()));
        this.textFieldValorReceita.setText(String.valueOf(receita.getValor()));
    }  
}
