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
import model.Despesa;

public class TelaAdicionarDespesaController implements Initializable {
    
    @FXML
    private AnchorPane anchorPaneTelaAdicionarDespesa;
    
    @FXML
    private Button buttonConfirmar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Label labelStatusCadastroSalgado;

    @FXML
    private TextField textFieldNomeDespesa;

    @FXML
    private TextField textFieldValorDespesa;
       
    @FXML
    private Text textCamposVazios;
    
    private Despesa despesa;
    
    private ControleDespesa controleDespesa = new ControleDespesa();
    
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
        if (!textFieldNomeDespesa.getText().equals("") && (!textFieldValorDespesa.getText().equals(""))) {
            
            String nome = textFieldNomeDespesa.getText().toUpperCase();
            double valor = Double.parseDouble(textFieldValorDespesa.getText());
            this.despesa=new Despesa(nome,  valor);
            
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
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        dialogStage.setResizable(false);
    }
    
    public Stage getDialogStage() {
        return dialogStage;
    }
    
}
