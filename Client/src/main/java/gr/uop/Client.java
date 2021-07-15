package gr.uop;

import gr.uop.Catalogue;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * JavaFX App
 */
public class Client extends Application
{
    private VBox keyboard = new VBox();
    private GridPane main = new GridPane();
    private TextField textDisplay = new TextField();
    private String text="";
    private Stage stage;
    static HashMap<String,Integer> data = new HashMap<>();
    static LinkedList<String> tmp = new LinkedList<>();
    private boolean connection = false;
    private boolean initiatedTemp = false;

    @Override
    public void start(Stage stage)
    {
        createKeyboardLayout(); //refactor

        createLicencePlateDisplay();



        var scene = new Scene(main, 1024, 768);


        stage.setScene(scene);
        stage.setTitle("Client");
        stage.show();
    }

    private void createLicencePlateDisplay()
    {
        textDisplay.setPrefWidth(150);
        textDisplay.setPromptText("Παρακαλώ εισάγετε τον αριθμό κυκλοφορίας");
        textDisplay.setPadding(new Insets(10,10,10,10));
        main.add(textDisplay, 0, 0);
        main.setAlignment(Pos.CENTER);
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
                        {"C", "V", "B", "N", "M", "SPACE","BACKSPACE","CATALOGUE","ENTER"}
                };

        //FirstRow
        for(int j =0; j<letters[0].length; j++)
        {
            Button button = new Button(letters[0][j]);
            button.setPadding(new Insets(35));
            firstRow.getChildren().add(button);

            button.setOnAction(event ->
            {
                text += ((Button) event.getSource()).getText();
                textDisplay.setText(text);
            });
        }
        for(int j =0; j<3; j++)
        {
            Button button = new Button(numbers[j]);

            button.setPadding(new Insets(35));

            firstRow.getChildren().add(button);

            button.setOnAction(event ->
            {
                text += ((Button) event.getSource()).getText();
                textDisplay.setText(text);
            });
        }
        keyboard.getChildren().add(firstRow);
        //FirstRow

        //SecondRow
        for(int j =0; j<letters[1].length; j++)
        {
            Button button = new Button(letters[1][j]);

            button.setPadding(new Insets(35));
            secondRow.getChildren().add(button);

            button.setOnAction(event ->
            {
                text += ((Button) event.getSource()).getText();
                textDisplay.setText(text);
            });
        }
        for(int j =3; j<6; j++)
        {
            Button button = new Button(numbers[j]);

            button.setPadding(new Insets(35));

            secondRow.getChildren().add(button);

            button.setOnAction(event ->
            {
                text += ((Button) event.getSource()).getText();
                textDisplay.setText(text);
            });
        }
        keyboard.getChildren().add(secondRow);
        //SecondRow

        //ThirdRow
        for(int j =0; j<letters[2].length; j++)
        {
            Button button = new Button(letters[2][j]);

            button.setPadding(new Insets(35));

            thirdRow.getChildren().add(button);

            button.setOnAction(event ->
            {
                text += ((Button) event.getSource()).getText();
                textDisplay.setText(text);
            });
        }
        for(int j =6; j<9; j++)
        {
            Button button = new Button(numbers[j]);

            button.setPadding(new Insets(35));

            thirdRow.getChildren().add(button);

            button.setOnAction(event ->
            {
                text += ((Button) event.getSource()).getText();
                textDisplay.setText(text);
            });
        }
        keyboard.getChildren().add(thirdRow);

        //ThirdRow

        //ForthRow
        for(int j =0; j<letters[3].length; j++)
        {
            Button button = new Button(letters[3][j]);

            button.setPadding(new Insets(36));

            forthRow.getChildren().add(button);
            forthRow.setSpacing(1);

            button.setOnAction(event ->
            {
                //Μενει να δω πως θα υλοποιησω το Enter

                String buttonText = ((Button) event.getSource()).getText();
                if(buttonText.equals("SPACE"))
                    text += " ";
                else if(buttonText.equals("BACKSPACE"))
                {
                    try
                    {
                        text = text.substring(0, text.length() - 1);
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        System.err.println(e.getCause());
                    }
                }
                else if(buttonText.equals("CATALOGUE"))
                {

                    if(text.isEmpty())
                    {
                        alertError(stage,"Παρακαλώ δώστε αριθμό κυκλοφορίας");
                    }
                    else if(text.length() == 1)
                    {
                        alertError(stage,"Ο αριθμός κυκλοφορίας πρέπει να έχει τουλάχιστον 2 χαρακτήρες");
                    }
                    else
                        Catalogue.start();

                }
                else if (buttonText.equals("ENTER"))
                {


                    try (Socket socket = new Socket("localhost", 5555);
                         PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
                    ) {

                        if (!initiatedTemp) {
                            connection = true;
                            String send = text;
                            int value = Catalogue.getCost();
                            //String server = Services.getS();
                            data.put(text, value);
                            System.out.println("We entered");
                            String date = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
                            toServer.println(text + " " + value + " " + date);
                        }else {
                            connection = true;
                            String send = text;
                            int value = Catalogue.getCost();
                            //String server = Services.getS();


                            String date = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
                            tmp.add(text + " " + value + " " + date);
                            System.out.println("CALLED");

                            for (String s : tmp){
                                toServer.println(s);


                            }

                            initiatedTemp = false;
                        }

                    } catch (IOException e) {
                        System.out.println("Connection to the server failed. Starting storing data to temporary memory");

                        String send = text;
                        int value = Catalogue.getCost();
                        //String server = Services.getS();

                        System.out.println("STORING");
                        String date = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
                        tmp.add(text + " " + value + " " + date);
                        for (String s : tmp){
                            System.out.println(s);
                        }
                        initiatedTemp = true;

                    }
                }


                else {

                    textDisplay.setText(text);
                }


            });
        }
        keyboard.getChildren().add(forthRow);
        //ForthRow


        main.add(keyboard, 0, 1);
        main.setVgap(17);
        main.setAlignment(Pos.CENTER);
    }

    private void alertError(Stage stage , String errorString)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Σφάλμα");
        alert.setContentText(errorString);
        alert.setHeaderText(null);
        alert.initOwner(stage);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
