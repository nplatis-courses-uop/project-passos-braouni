package gr.uop;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Server extends Application {

    @Override
    public void start(Stage stage) 
    {
        
        BorderPane main = new BorderPane();

        HBox firstRow = new HBox();
        HBox secondRow = new HBox();
        HBox thirdRow = new HBox();
        HBox forthRow = new HBox();
        GridPane keyboard = new GridPane();

        final String[] numbers = new String[]{
            "7", "8", "9",
            "4", "5", "6",
            "3", "2", "1"};
        
        final String[][] letters = new String[][]
        {
            {"Q", "W", "E", "R", "T", "Y", "U"}, 
            {"I", "O", "P", "A", "S", "D", "F"},
            {"G", "H", "J", "K", "L", "Z", "X"},
            {"C", "V", "B", "N", "M", "SPACE ", "BACKSPACE"}
        };

        //FirstRow
        for(int j =0; j<letters[0].length; j++)
        {
            firstRow.getChildren().add(new Button(letters[0][j]));
        }
        for(int j =0; j<3; j++)
        {
            firstRow.getChildren().add(new Button(numbers[j]));
        }
        keyboard.add(firstRow,1,1,1,1);
        //FirstRow
        
        //SecondRow
        for(int j =0; j<letters[1].length; j++)
        {
            secondRow.getChildren().add(new Button(letters[1][j]));
        }
        for(int j =3; j<6; j++)
        {
            secondRow.getChildren().add(new Button(numbers[j]));
        }
        keyboard.add(secondRow,1,2,1,1);
        //SecondRow

        //ThirdRow
        for(int j =0; j<letters[2].length; j++)
        {
            thirdRow.getChildren().add(new Button(letters[2][j]));
        }
        for(int j =6; j<9; j++)
        {
            thirdRow.getChildren().add(new Button(numbers[j]));
        }
        keyboard.add(thirdRow,1,3,1,1);
        //ThirdRow

        //ForthRow
        for(int j =0; j<letters[3].length; j++)
        {
            forthRow.getChildren().add(new Button(letters[3][j]));
        }
        keyboard.add(forthRow,1,4,1,1);
        //ForthRow
        
        main.setBottom(keyboard);

        var scene = new Scene(main, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Client");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
