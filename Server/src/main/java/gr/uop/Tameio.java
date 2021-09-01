package gr.uop;

import gr.uop.DummyClass;
import gr.uop.History;
import gr.uop.Server;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tameio extends Application {
    public static List<String> list = new ArrayList<String>();
    public static ObservableList<String> obs = FXCollections.observableArrayList(list);
    TableView<DummyClass> elements;

    @Override
    public void start(Stage stage) throws IOException {
        Server server = new Server();

        TableColumn<DummyClass,String> column1 = new TableColumn<>("ΠΙΝΑΚΙΔΑ");
        column1.setMinWidth(200.00);
        column1.setCellValueFactory(new PropertyValueFactory<>("first"));
        TableColumn<DummyClass,String > column2 = new TableColumn<>("ΚΟΣΤΟΣ");
        column2.setMinWidth(200.00);
        column2.setCellValueFactory(new PropertyValueFactory<>("second"));
        TableColumn<DummyClass,String > column3 = new TableColumn<>("ΩΡΑ ΑΦΙΞΗΣ");
        column3.setMinWidth(200.00);
        column3.setCellValueFactory(new PropertyValueFactory<>("third"));
        TableColumn<DummyClass,String > column4 = new TableColumn<>("ΥΠΗΡΕΣΙΑ");
        column4.setMinWidth(200.00);
        column4.setCellValueFactory(new PropertyValueFactory<>("fourth"));

        elements = new TableView<>();
        ObservableList<DummyClass> services = FXCollections.observableArrayList();


        elements.getColumns().add(column1);
        elements.getColumns().add(column2);
        elements.getColumns().add(column3);
        elements.getColumns().add(column4);
        elements.setStyle("-fx-alignment:CENTER;");
        column1.setStyle("-fx-alignment:CENTER;");
        column2.setStyle("-fx-alignment:CENTER;");
        column3.setStyle("-fx-alignment:CENTER;");
        column4.setStyle("-fx-alignment:CENTER;");



        GridPane grid = new GridPane();


        onStartUp();



        grid.add(elements, 0, 1, 7, 7);
        BorderPane pane = new BorderPane();
        pane.setCenter(elements);
        Button fullData = new Button("History");
        fullData.setStyle("-fx-color:red;-fx-font-style:italic;-fx-pref-width:100px;-fx-pref-height:50px;-fx-alignment:center;-fx-font-size:16px");
        fullData.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fullData.setStyle("-fx-color:green;-fx-font-style:italic;-fx-pref-width:100px;-fx-pref-height:50px;-fx-alignment:center;-fx-font-size:16px");;
            }
        });
        fullData.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fullData.setStyle("-fx-color:red;-fx-font-style:italic;-fx-pref-width:100px;-fx-pref-height:50px;-fx-alignment:center;-fx-font-size:16px");
            }
        });
        fullData.minWidth(100.00);
        StackPane right = new StackPane();
        right.prefHeight(100.00);
        right.setMinHeight(100.00);
        right.getChildren().add(fullData);
        fullData.setAlignment(Pos.BASELINE_RIGHT);
        pane.setBottom(right);
        column1.prefWidthProperty().bind(elements.widthProperty().divide(4));
        column2.prefWidthProperty().bind(elements.widthProperty().divide(4));
        column3.prefWidthProperty().bind(elements.widthProperty().divide(4));
        column4.prefWidthProperty().bind(elements.widthProperty().divide(4));
        grid.maxWidth(1024.00);
        grid.prefWidth(1024.00);
        GridPane.setVgrow(grid, Priority.ALWAYS);

        var scene = new Scene(pane, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Ταμείο");
        stage.setMinWidth(1024.00);
        stage.setMinHeight(768.00);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920.00);
        stage.show();


        server.start();

        elements.setItems(getPro());

        elements.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                elements.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Επιβεβαίωση");
                alert.setContentText("Θέλετε να εκδώσετε απόδειξη;");
                alert.setHeaderText(null);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    magic(elements.getSelectionModel().getSelectedItem().toString());
                    obs.remove(elements.getSelectionModel().getSelectedItem().toString());
                    obs.removeAll(obs);
                    onStartUp();
                    elements.setItems(getPro());
                }



            }else if (event.getCode() == KeyCode.DELETE){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Διαγραφή");
                alert.setContentText("Είσαστε σίγουροι;");
                alert.setHeaderText(null);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    deleteElement(elements.getSelectionModel().getSelectedItem().toString());
                    obs.remove(elements.getSelectionModel().getSelectedItem().toString());
                    obs.removeAll(obs);
                    onStartUp();
                    elements.setItems(getPro());
                }



            }
        });
        obs.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                //System.out.println("AAA");
                while (change.next()) {
                    if (change.wasAdded()) {
                        //System.out.println("EEE");
                        int ch = change.getFrom();

                        elements.setItems(getPro());

                    }
                }

            }
        });
        elements.setItems(getPro());
        fullData.setOnAction(e->{
            try {
                new History().start(new Stage());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

    }

    public void add(String label) {
        obs.add(label);
    }

    public static void main(String[] args) {
        launch(args);

    }



    private void magic(String label)  {

        String t = label;
        t = t +  " @@";
        String date = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());

        File log= new File("test.txt");

        File textFile = new File("test.txt");
        String oldContent = "";
        try
        {
            File file = new File("test.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while((line = reader.readLine()) != null)
            {
                oldtext += line + "\r\n";
            }
            reader.close();

            String newtext = oldtext.replace(t,t.replace("@@",""+ date));

            FileWriter writer = new FileWriter("test.txt");
            writer.write(newtext);
            writer.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }



    }


    private void deleteElement(String label){
        String s = label;
        try {

            BufferedReader file = new BufferedReader(new FileReader("test.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                if (line.contains(s)){
                    line = "";
                    continue;
                }

                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();


            FileOutputStream fileOut = new FileOutputStream("test.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }


    }

    private void onStartUp() {

        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))){


            String line;
            while ((line = br.readLine())!=null){
                if (line.contains("@@")){
                    obs.add(line);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ObservableList<DummyClass> getPro(){
        ObservableList<DummyClass> products = FXCollections.observableArrayList();
        for (String l : obs){
            String input = l;
            String[] splitted = input.split(" ");

            products.add(new DummyClass(splitted[0],splitted[1],splitted[2],splitted[3]));
        }

        return products;


    }



}
