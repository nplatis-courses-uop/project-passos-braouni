package gr.uop;

import gr.uop.DummyClass;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class History extends Application {

    public static List<String> dataInput = new ArrayList<String>();
    public static ObservableList<String> fileData = FXCollections.observableArrayList(dataInput);

    TableView<DummyClass> table;


    @Override
    public void start(Stage stage) throws Exception {
        fileData.clear();
        HBox el = new HBox();


        readFile();



        TableColumn<DummyClass,String> column1 = new TableColumn<>("ΠΙΝΑΚΙΔΑ");
        column1.setMinWidth(150.00);
        column1.setCellValueFactory(new PropertyValueFactory<>("first"));
        TableColumn<DummyClass,String > column2 = new TableColumn<>("ΚΟΣΤΟΣ");
        column2.setMinWidth(100.00);
        column2.setCellValueFactory(new PropertyValueFactory<>("second"));
        TableColumn<DummyClass,String > column3 = new TableColumn<>("ΩΡΑ ΑΦΙΞΗΣ");
        column3.setMinWidth(150.00);
        column3.setCellValueFactory(new PropertyValueFactory<>("third"));
        TableColumn<DummyClass,String > column4 = new TableColumn<>("ΩΡΑ ΑΝΑΧΩΡΗΣΗΣ");
        column4.setMinWidth(150.00);
        column4.setCellValueFactory(new PropertyValueFactory<>("fourth"));
        TableColumn<DummyClass,String > column5 = new TableColumn<>("ΥΠΗΡΕΣΙΑ");
        column5.setMinWidth(150.00);
        column5.setCellValueFactory(new PropertyValueFactory<>("fifth"));

        table = new TableView<>();
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.getColumns().add(column5);
        table.setStyle("-fx-alignment:CENTER;");
        column1.setStyle("-fx-alignment:CENTER;");
        column2.setStyle("-fx-alignment:CENTER;");
        column3.setStyle("-fx-alignment:CENTER;");
        column4.setStyle("-fx-alignment:CENTER;");
        column5.setStyle("-fx-alignment:CENTER;");

        table.setItems(getContent());
        el.getChildren().add(table);
        column1.prefWidthProperty().bind(el.widthProperty().divide(5));
        column2.prefWidthProperty().bind(el.widthProperty().divide(5));
        column3.prefWidthProperty().bind(el.widthProperty().divide(5));
        column4.prefWidthProperty().bind(el.widthProperty().divide(5));
        column5.prefWidthProperty().bind(el.widthProperty().divide(5));

        var scene = new Scene(el, 1024, 768);

        stage.setMinWidth(1024.00);
        stage.setMinHeight(768.00);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920.00);
        stage.setScene(scene);
        stage.setTitle("Βιβλίο Καταχωρήσεων");

        stage.show();

        fileData.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {

                while (change.next()){
                    if (change.wasAdded()){


                        int ch = change.getFrom();

                    }
                }
            }
        });


    }

    public static void readFile()
    {
        File fl = new File("test.txt");
        try {
            Scanner scanner = new Scanner(fl);
            while (scanner.hasNext()){
                String out = scanner.nextLine();

                fileData.add(out);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<DummyClass> getContent(){
        ObservableList<DummyClass> products = FXCollections.observableArrayList();
        for (String l : fileData){
            String input = l;
            String[] splitted = input.split(" ");
            if (splitted[4].equals("@@")){
                products.add(new DummyClass(splitted[0],splitted[1],splitted[2],"Αναμονή Πληρωμής",splitted[3]));
            }else{

                products.add(new DummyClass(splitted[0],splitted[1],splitted[2],splitted[4],splitted[3]));
            }


        }

        return products;


    }



}
