package view;

import java.io.IOException;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene; 
import javafx.stage.Stage;


public class DeyeD_JavaFX extends Application {
    private static Scene scene1;
    private static Scene scene2;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root1 = FXMLLoader.load (getClass().getResource("/fxml/Carregamento.fxml"));
        scene1 = new Scene(root1);

        Parent root2 = FXMLLoader.load (getClass().getResource("/fxml/Login.fxml"));
        scene2 = new Scene(root2);

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> stage.setScene(scene2));

        stage.setTitle("DeyeD Gerenciador Financeiro");
        stage.setResizable(false);
        stage.setScene(scene1);
        stage.show();

        delay.play();

    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
