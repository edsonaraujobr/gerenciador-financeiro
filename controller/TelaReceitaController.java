package controller;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import model.Receita;

public class TelaReceitaController implements Initializable {
    
    @FXML
    private ImageView glowEditar;

    @FXML
    private ImageView glowExcluir;

    @FXML
    private AnchorPane anchorPaneTelaReceita;
    
    @FXML
    private Button buttonAdicionarReceita;

    @FXML
    private Button buttonSair;

    @FXML
    private TableColumn<?, Date> tableColumnReceitaData;

    @FXML
    private TableColumn<?, ?> tableColumnReceitaDescricao;

    @FXML
    private TableColumn<?, ?> tableColumnReceitaValor;

    @FXML
    private TableView<Receita> tableReceita;

    @FXML
    private TextField textFieldPesquisarReceita;

    @FXML
    private Label labelReceitaAtual;
      
    @FXML
    private Button buttonTelaInicial;
    
    @FXML
    private ImageView buttonDeletar;

    @FXML
    private ImageView buttonEditar;
    
    private ControleReceita controleReceita = new ControleReceita();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarTableReceita();
        try {
            carregarReceitaAtual();
        } catch (Exception ex) {
            Logger.getLogger(TelaDespesaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregarReceitaAtual() throws Exception {
        ArrayList<Receita> receitas = (ArrayList<Receita>)controleReceita.listarReceitas();
        double receitaTotal=0;
        for (int c = 0; c < receitas.size();c++ ) {
            receitaTotal += receitas.get(c).getValor();
        }
   
       labelReceitaAtual.setText(String.valueOf(receitaTotal));
       labelReceitaAtual.setTextFill(Color.LIGHTGREEN);
       
    }
    
    private void carregarTableReceita() {
        tableColumnReceitaDescricao.setCellValueFactory(new PropertyValueFactory("nome"));
        tableColumnReceitaValor.setCellValueFactory(new PropertyValueFactory("valor"));
        tableColumnReceitaData.setCellValueFactory(new PropertyValueFactory("data"));
        try {
            ArrayList<Receita> listReceitas = (ArrayList<Receita>)controleReceita.listarReceitas();
            ObservableList observableListReceitas = FXCollections.observableArrayList(listReceitas);
            tableReceita.setItems(observableListReceitas);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }    
    
    @FXML
    private void handleButtonSair(ActionEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/Login.fxml"));
            anchorPaneTelaReceita.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleButtonTelaInicial(ActionEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/TelaInicial.fxml"));
            anchorPaneTelaReceita.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleButtonAdicionarDespesa(ActionEvent event) throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TelaAdicionarReceitaController.class.getResource("/fxml/TelaAdicionarReceita.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Adicione Receita");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        TelaAdicionarReceitaController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();
        Receita receita=controller.getReceita();
        if (receita!=null){
            this.controleReceita.adicionarReceita(receita);
        }   
        carregarTableReceita();
        carregarReceitaAtual();   
    }
     
    @FXML
    private void clicouEmEditar(MouseEvent event) throws IOException, Exception { 
        Receita receita = (Receita) tableReceita.getSelectionModel().getSelectedItem();
        if (receita != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(telaEditarReceitaController.class.getResource("/fxml/telaEditarReceita.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Receita - Editar");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            telaEditarReceitaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReceita(receita);

            dialogStage.showAndWait();
            this.controleReceita.atualizarReceita(controller.getReceita());
            carregarTableReceita();
            carregarReceitaAtual();
        }
        else{
             JOptionPane.showMessageDialog(null, "Nenhuma receita selecionada", "ERRO!", 2);
        }
    }
 

    @FXML
    private void clicouEmExcluir(MouseEvent event) throws Exception {
        Receita receita = (Receita) tableReceita.getSelectionModel().getSelectedItem();
        if (receita != null){
            controleReceita.excluirReceita(receita);
            carregarTableReceita();
            carregarReceitaAtual();
        }
        else{
            JOptionPane.showMessageDialog(null, "Nenhuma receita selecionada", "ERRO!", 2);
        }
    }
    
    @FXML
    void handlePesquisarReceita(KeyEvent event) throws Exception {
        ObservableList obsListReceita = FXCollections.observableArrayList();
        ArrayList<Receita> listReceitas = (ArrayList<Receita>)controleReceita.listarReceitas();
        
        String nome = textFieldPesquisarReceita.getText().toUpperCase();
        if (!nome.isEmpty()){
            for (Receita s : listReceitas){
                if (s.getNome().startsWith(nome))
                    obsListReceita.add(s);
            }
            tableReceita.setItems(obsListReceita);
        }
        else{
            carregarTableReceita();
        }
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
