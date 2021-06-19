package gr.uop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Server extends Application 
{
    @Override
    public void start(Stage stage) 
    {
        BorderPane borderpane = new BorderPane();
        var scene = new Scene(borderpane, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Client");
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }

    
}
