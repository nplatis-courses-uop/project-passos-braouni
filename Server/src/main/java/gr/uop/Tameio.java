package gr.uop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Tameio extends Application {
    public static List<Label> list = new ArrayList<Label>();
    public static ObservableList<Label> obs = FXCollections.observableArrayList(list);
    public static List<Label> ano = new ArrayList<Label>();
    public static ObservableList<Label> cost = FXCollections.observableArrayList(ano);
    public static List<Label> da = new ArrayList<Label>();
    public static ObservableList<Label> date = FXCollections.observableArrayList(da);

    @Override
    public void start(Stage stage) throws IOException {
        Server server = new Server();
        ListView<Label> elements = new ListView<>();
        ListView<Label> kostos = new ListView<>();
        ListView<Label> day = new ListView<>();
        TableView<Label> tr = new TableView<>();

        GridPane grid = new GridPane();
        Label label1 = new Label("NUM");
        Label label2 = new Label("Pinakida");
        Label label3 = new Label("Ipiresies");
        Label label4 = new Label("Kostos");
        Button label5 = new Button("OK");
        Button label6 = new Button("NO");
        //this.obs.add(new Label("hi"));
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(16);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(16);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(16);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(16);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(16);
        ColumnConstraints col6 = new ColumnConstraints();
        col6.setPercentWidth(16);
        grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);

        grid.add(label1, 0, 0, 1, 1);
        grid.add(label2, 1, 0, 1, 1);
        grid.add(label3, 2, 0, 1, 1);
        grid.add(label4, 3, 0, 1, 1);
        grid.add(label5, 4, 0, 1, 1);
        grid.add(label6, 5, 0, 1, 1);
        grid.add(elements, 0, 1, 4, 4);
//        grid.add(kostos,1,1,1,1);
//        grid.add(day,2,1,1,1);
        //grid.add(tr,0,1,4,4);
        //grid.add(this.obs.get(0),0,1,1,2);
        grid.maxWidth(1024.00);
        grid.prefWidth(1024.00);
        GridPane.setVgrow(grid, Priority.ALWAYS);
        BorderPane borderpane = new BorderPane();
        var scene = new Scene(grid, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Client");
        stage.show();


        server.start();

        obs.addListener(new ListChangeListener<Label>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Label> change) {
                System.out.println("AAA");
                while (change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("EEE");
                        int ch = change.getFrom();
                        System.out.println(change.getList().get(ch));
                        //grid.add(obs.get(ch),0,1,1,2);
                        //elements.add(change.getList().get(ch));
                        //updateGrid(grid,change.getList().get(ch));
                    }
                }

            }
        });
        cost.addListener(new ListChangeListener<Label>() {
            @Override
            public void onChanged(Change<? extends Label> change) {
                System.out.println("AAA");
                while (change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("EEE");
                        int ch = change.getFrom();
                        System.out.println(change.getList().get(ch));
                        //grid.add(obs.get(ch),0,1,1,2);
                        //elements.add(change.getList().get(ch));
                        //updateGrid(grid,change.getList().get(ch));
                    }
                }

            }
        });
        date.addListener(new ListChangeListener<Label>() {
            @Override
            public void onChanged(Change<? extends Label> change) {
                System.out.println("AAA");
                while (change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("EEE");
                        int ch = change.getFrom();
                        System.out.println(change.getList().get(ch));
                        //grid.add(obs.get(ch),0,1,1,2);
                        //elements.add(change.getList().get(ch));
                        //updateGrid(grid,change.getList().get(ch));
                    }
                }

            }
        });


        //tr.setItems(obs);
        elements.setItems(obs);
        kostos.setItems(cost);
        day.setItems(date);

        elements.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                elements.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.out.println("Pressed");
                    magic(elements.getSelectionModel().getSelectedItem());
                    obs.remove(elements.getSelectionModel().getSelectedItem());
                }
            }
        });

    }

    public void add(Label label) {
        obs.add(label);
    }

    public static void main(String[] args) {
        launch(args);

    }



    private void magic(Label label)  {

        String t = label.getText();
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

            String newtext = oldtext.replaceAll(t, t.replace("@@","") + " " + date);

            FileWriter writer = new FileWriter("test.txt");
            writer.write(newtext);writer.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }



    }

}
