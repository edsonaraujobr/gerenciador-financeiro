package controller;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Despesa;
import model.Receita;

public class TelaInicialController implements Initializable {

    @FXML
    private TableView<?> tableDespesa;

    @FXML
    private TableView<?> tableReceita;
    
    @FXML
    private TableColumn<?, ?> tableColumnDespesaData;

    @FXML
    private TableColumn<?, ?> tableColumnDespesaDescricao;

    @FXML
    private TableColumn<?, ?> tableColumnDespesaValor;

    @FXML
    private TableColumn<?, ?> tableColumnReceitaData;

    @FXML
    private TableColumn<?, ?> tableColumnReceitaDescricao;

    @FXML
    private TableColumn<?, ?> tableColumnReceitaValor;

    @FXML
    private ImageView setaMostrarDespesa;

    @FXML
    private ImageView setaMostrarReceita;

    @FXML
    private AnchorPane anchorTableDespesa;

    @FXML
    private AnchorPane anchorTableReceita;
    
    @FXML
    private ImageView setaOcultarDespesa;

    @FXML
    private ImageView setaOcultarReceita;

    @FXML
    private ImageView glowDespesa;

    @FXML
    private ImageView glowReceita;

    @FXML
    private ImageView glowPlus;

    @FXML
    private Line lineDespesa;

    @FXML
    private Line lineReceita;
    
    @FXML
    private AnchorPane anchorPaneTelaInicial;
    
    @FXML
    private ImageView buttonAdicionar;

    @FXML
    private Button buttonDespesa;

    @FXML
    private Button buttonReceita;

    @FXML
    private Button buttonSair;
    
    @FXML
    private Label labelSaldoAtual;

    @FXML
    private ImageView buttonAdicionarDespesa;

    @FXML
    private ImageView buttonAdicionarReceita;
    
    public static ControleDespesa controleDespesa = new ControleDespesa();
    public static ControleReceita controleReceita = new ControleReceita();
    
    private int clicou = 0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregarSaldoAtual();
        } catch (Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregarSaldoAtual() throws Exception {
        ArrayList<Despesa> despesas = (ArrayList<Despesa>)controleDespesa.listarDespesas();
        double despesaTotal=0;
        for (int c = 0; c < despesas.size();c++ ) {
            despesaTotal += despesas.get(c).getValor();
        }
        ArrayList<Receita> receitas = (ArrayList<Receita>)controleReceita.listarReceitas();
        double receitaTotal=0;
        for (int c = 0; c < receitas.size();c++ ) {
            receitaTotal += receitas.get(c).getValor();
        }
   
       labelSaldoAtual.setText(String.valueOf(receitaTotal-despesaTotal));
       if (0>(receitaTotal-despesaTotal)) {
            labelSaldoAtual.setTextFill(Color.RED);
       }
       else {
           labelSaldoAtual.setTextFill(Color.LIGHTGREEN);
       }
       
    }

    @FXML
    public void handleButtonSair(ActionEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/Login.fxml"));
            anchorPaneTelaInicial.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleButtonDespesa(ActionEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/TelaDespesa.fxml"));
            anchorPaneTelaInicial.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    void handleButtonReceita(ActionEvent event) {
        AnchorPane a;
        try {
            a = (AnchorPane)FXMLLoader.load (getClass().getResource("/fxml/TelaReceita.fxml"));
            anchorPaneTelaInicial.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleButtonAdicionar(MouseEvent event) {
        if(clicou%2==0){
            lineDespesa.setVisible(true);
            lineReceita.setVisible(true);
            glowPlus.setVisible(true);
            buttonAdicionarReceita.setVisible(true);
            buttonAdicionarDespesa.setVisible(true);
            clicou++;
        }else{        
            lineDespesa.setVisible(false);
            lineReceita.setVisible(false);
            glowPlus.setVisible(false);
            buttonAdicionarReceita.setVisible(false);
            buttonAdicionarDespesa.setVisible(false);
            clicou++;
        }
        
    }

    @FXML
    void handleButtonAdicionarDespesa(MouseEvent event) throws IOException, Exception {
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
        carregarSaldoAtual();
        carregarTableDespesa();
    }

    @FXML
    void handleButtonAdicionarReceita(MouseEvent event) throws IOException, Exception {
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
        carregarSaldoAtual();
        carregarTableReceita();
    }
    
    @FXML
    void passouOMouseEmReceita(MouseEvent event) {
        glowReceita.setVisible(true);
    }
    
    @FXML
    void retirouOMouseEmReceita(MouseEvent event) {
        glowReceita.setVisible(false);
    }

    @FXML
    void passouOMouseEmDespesa(MouseEvent event) {
        glowDespesa.setVisible(true);
    }
    
    @FXML
    void retirouOMouseEmDespesa(MouseEvent event) {
        glowDespesa.setVisible(false);
    }

    @FXML
    void clicouEmMostrarReceita(MouseEvent event) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(.2), anchorTableReceita);
        scaleTransition.setFromY(0);
        scaleTransition.setToY(1);
        scaleTransition.setInterpolator(Interpolator.LINEAR);
        scaleTransition.play();
        setaOcultarReceita.setVisible(true);
        setaMostrarReceita.setVisible(false);
        anchorTableReceita.setVisible(true);
        carregarTableReceita();
    }
    
    @FXML
    void clicouEmOcultarReceita(MouseEvent event) {
        setaOcultarReceita.setVisible(false);
        setaMostrarReceita.setVisible(true);
        anchorTableReceita.setVisible(false);
    }

    @FXML
    void clicouEmMostrarDespesa(MouseEvent event) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(.2), anchorTableDespesa);
        scaleTransition.setFromY(0);
        scaleTransition.setToY(1);
        scaleTransition.setInterpolator(Interpolator.LINEAR);
        scaleTransition.play();
        setaOcultarDespesa.setVisible(true);
        setaMostrarDespesa.setVisible(false);
        anchorTableDespesa.setVisible(true);
        carregarTableDespesa();
    }
    
   @FXML
    void clicouEmOcultarDespesa(MouseEvent event) {
        setaOcultarDespesa.setVisible(false);
        setaMostrarDespesa.setVisible(true);
        anchorTableDespesa.setVisible(false);
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
}
