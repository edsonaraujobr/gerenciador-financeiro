package controller;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Despesa;

public class TelaDespesaController implements Initializable {

    @FXML
    private AnchorPane anchorPaneTelaDespesa;
    
    @FXML
    private Button buttonAdicionarDespesa;

    @FXML
    private Button buttonSair;

    @FXML
    private TableColumn<?, ?> tableColumnDespesaData;

    @FXML
    private TableColumn<?, ?> tableColumnDespesaDescricao;

    @FXML
    private TableColumn<?, ?> tableColumnDespesaValor;

    @FXML
    private TableView<?> tableDespesa;

    @FXML
    private Label labelDespesaAtual;

    @FXML
    private TextField textFieldPesquisaDespesa;
    
    @FXML
    private Button buttonTelaInicial;
        
    @FXML
    private ImageView buttonDeletar;

    @FXML
    private ImageView buttonEditar;
    
    @FXML
    private ImageView glowEditar;

    @FXML
    private ImageView glowExcluir;

    private ControleDespesa controleDespesa = new ControleDespesa();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarTableDespesa();
        try {
            carregarDespesaAtual();
        } catch (Exception ex) {
            Logger.getLogger(TelaDespesaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregarDespesaAtual() throws Exception {
        ArrayList<Despesa> despesas = (ArrayList<Despesa>)controleDespesa.listarDespesas();
        double despesaTotal=0;
        for (int c = 0; c < despesas.size();c++ ) {
            despesaTotal += despesas.get(c).getValor();
        }
   
       labelDespesaAtual.setText(String.valueOf(despesaTotal));
       labelDespesaAtual.setTextFill(Color.RED);
       
    }
    
    private void carregarTableDespesa() {
        tableColumnDespesaDescricao.setCellValueFactory(new PropertyValueFactory("nome"));
        tableColumnDespesaValor.setCellValueFactory(new PropertyValueFactory("valor"));
        tableColumnDespesaData.setCellValueFactory(new PropertyValueFactory("data"));
        try {
            ArrayList<Despesa> listDespesas = (ArrayList<Despesa>)controleDespesa.listarDespesas();
            ObservableList observableListDespesas = FXCollections.observableArrayList(listDespesas);
            tableDespesa.setItems(observableListDespesas);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    @FXML
    void handlePesquisarDespesa(KeyEvent event) throws Exception {
        ObservableList obsListDespesa = FXCollections.observableArrayList();
        ArrayList<Despesa> listDespesas = (ArrayList<Despesa>)controleDespesa.listarDespesas();
        
        String nome = textFieldPesquisaDespesa.getText().toUpperCase();
        if (!nome.isEmpty()){
            for (Despesa s : listDespesas){
                if (s.getNome().startsWith(nome))
                    obsListDespesa.add(s);
            }
            tableDespesa.setItems(obsListDespesa);
        }
        else{
            carregarTableDespesa();
        }
    }
    
    @FXML
    void handleButtonAdicionarDespesa(ActionEvent event) throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TelaAdicionarDespesaController.class.getResource("/fxml/TelaAdicionarDespesa.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Adicione Despesa");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        TelaAdicionarDespesaController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();
        Despesa despesa=controller.getDespesa();
        if (despesa!=null){
            this.controleDespesa.adicionarDespesa(despesa);
        }   
        carregarTableDespesa();
        carregarDespesaAtual();
    }
    

    @FXML
    void handleButtonSair(ActionEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/Login.fxml"));
            anchorPaneTelaDespesa.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleButtonTelaInicial(ActionEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/TelaInicial.fxml"));
            anchorPaneTelaDespesa.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @FXML
    private void clicouEmEditar(MouseEvent event) throws IOException, Exception {
       Despesa despesa = (Despesa) tableDespesa.getSelectionModel().getSelectedItem();
        if (despesa != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(telaEditarDespesaController.class.getResource("/fxml/telaEditarDespesa.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Despesa - Editar");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            telaEditarDespesaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDespesa(despesa);

            dialogStage.showAndWait();
            this.controleDespesa.atualizarDespesa(controller.getDespesa());
            carregarTableDespesa();
            carregarDespesaAtual();
        }
        else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Nenhuma seleção");
             alert.setHeaderText("Nenhuma receita foi selecionada");
             alert.setContentText("Por favor, selecione uma receita na tabela.");
             alert.showAndWait();
        }
    }

    @FXML
    private void clicouEmExcluir(MouseEvent event) throws Exception {
        Despesa despesa = (Despesa) tableDespesa.getSelectionModel().getSelectedItem();
        controleDespesa.excluirDespesa(despesa);
        carregarTableDespesa();
        carregarDespesaAtual();
    }
    
    @FXML
    void passouEmEditar(MouseEvent event) {
        glowEditar.setVisible(true);
    }
    
    @FXML
    void saiuDeEditar(MouseEvent event) {
        glowEditar.setVisible(false);
    }

    @FXML
    void passouEmExcluir(MouseEvent event) {
        glowExcluir.setVisible(true);
    }
    
    @FXML
    void saiuDeExcluir(MouseEvent event) {
        glowExcluir.setVisible(false);
    }


}
