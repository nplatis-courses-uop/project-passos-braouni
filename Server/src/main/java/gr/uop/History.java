package gr.uop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class History extends Application {

    public static List<Label> dataInput = new ArrayList<Label>();
    public static ObservableList<Label> fileData = FXCollections.observableArrayList(dataInput);
    @Override
    public void start(Stage stage) throws Exception {

        VBox el = new VBox();
        ListView<Label> tableView = new ListView<>();
        el.setPrefWidth(768);
        el.getChildren().add(tableView);
        readFile();

        //fileData.addAll(dataInput);
        //tableView.setItems(fileData);

        var scene = new Scene(el, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Client");
        stage.show();

        fileData.addListener(new ListChangeListener<Label>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Label> change) {
                System.out.println("HUIU");
                while (change.next()){
                    if (change.wasAdded()){

                        System.out.println("EEE");
                        int ch = change.getFrom();
                        System.out.println(change.getList().get(ch));
                    }
                }
            }
        });

        tableView.setItems(fileData);







    }

    public static void readFile()
    {
        File fl = new File("test.txt");
        try {
            Scanner scanner = new Scanner(fl);
            while (scanner.hasNext()){
                String out = scanner.nextLine();

                fileData.add(new Label(out));
                //fileData.add(out);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //public static void add(String s)
//    {
//        fileData.add(s);
//    }


}
