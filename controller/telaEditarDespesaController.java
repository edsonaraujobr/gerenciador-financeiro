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
import model.Despesa;

public class telaEditarDespesaController implements Initializable {
    
    @FXML
    private AnchorPane anchorPaneTelaEditarDespesa;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonSalvar;

    @FXML
    private TextField textFieldNomeDespesa;

    @FXML
    private TextField textFieldValorDespesa;
    
    @FXML
    private Text textCamposVazios;

    @FXML
    private Text textValorValido;
  
    private ControleDespesa controleDespesa = new ControleDespesa();
    
    private Stage dialogStage;
    
    private Despesa despesa;
    
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
        if (!textFieldNomeDespesa.getText().equals("") && (!textFieldValorDespesa.getText().equals(""))) {
            this.despesa.setNome(this.textFieldNomeDespesa.getText().toUpperCase());
            this.despesa.setValor(Double.parseDouble(this.textFieldValorDespesa.getText())); 
            this.dialogStage.close();
        } else {
            textCamposVazios.setVisible(true);
            textFieldNomeDespesa.requestFocus();
        }
                
    }
    
    private void limparCampos(){     
       textFieldNomeDespesa.clear();
       textFieldValorDespesa.clear();   
       textFieldNomeDespesa.requestFocus();
    }
    
    public Despesa getDespesa() {
         return despesa;
    }
    
    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
        this.textFieldNomeDespesa.setText(String.valueOf(despesa.getNome()));
        this.textFieldValorDespesa.setText(String.valueOf(despesa.getValor()));
    }

}

