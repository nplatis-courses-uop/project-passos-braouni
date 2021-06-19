package gr.uop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Client extends Application 
{
    private VBox keyboard = new VBox();
    private GridPane main = new GridPane();

    @Override
    public void start(Stage stage) 
    {
        createKeyboardLayout();

        var scene = new Scene(main, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Client");
        stage.show();
    }

    private void createKeyboardLayout()
    {

        HBox firstRow = new HBox(1.5);
        HBox secondRow = new HBox(2);
        HBox thirdRow = new HBox(2);
        HBox forthRow = new HBox(2);

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
            Button button = new Button(letters[0][j]);

            button.setPadding(new Insets(35));
            firstRow.getChildren().add(button);
        }
        for(int j =0; j<3; j++)
        {
            Button button = new Button(numbers[j]);

            button.setPadding(new Insets(35));

            firstRow.getChildren().add(button);
        }
        keyboard.getChildren().add(firstRow);
        //FirstRow
        
        //SecondRow
        for(int j =0; j<letters[1].length; j++)
        {
            Button button = new Button(letters[1][j]);

            button.setPadding(new Insets(35));
            secondRow.getChildren().add(button);
        }
        for(int j =3; j<6; j++)
        {
            Button button = new Button(numbers[j]);

            button.setPadding(new Insets(35));

            secondRow.getChildren().add(button);
        }
        keyboard.getChildren().add(secondRow);
        //SecondRow

        //ThirdRow
        for(int j =0; j<letters[2].length; j++)
        {
            Button button = new Button(letters[2][j]);

            button.setPadding(new Insets(35));

            thirdRow.getChildren().add(button);
        }
        for(int j =6; j<9; j++)
        {
            Button button = new Button(numbers[j]);

            button.setPadding(new Insets(35));
            
            thirdRow.getChildren().add(button);
        }
        keyboard.getChildren().add(thirdRow);
    
        //ThirdRow

        //ForthRow
        for(int j =0; j<letters[3].length; j++)
        {
            Button button = new Button(letters[3][j]);

            button.setPadding(new Insets(34));

            forthRow.getChildren().add(button);
        }
        keyboard.getChildren().add(forthRow);
        //ForthRow

        main.add(keyboard, 0, 0);
        main.setAlignment(Pos.BOTTOM_CENTER);
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
