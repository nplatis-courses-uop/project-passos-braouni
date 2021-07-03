package gr.uop;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Catalogue extends Client
{
    public static void start()
    {

        Stage stage = new Stage();
        StackPane pane = new StackPane();

        TableView table = new TableView<>();

        TableColumn<Services,String> servicesColumn = new TableColumn<>("");
        servicesColumn.setCellValueFactory(new PropertyValueFactory<Services,String>("service"));
        servicesColumn.setMaxWidth(200);

        TableColumn<Services,String> CarColumn = new TableColumn<>("Αμαξι");
        CarColumn.setCellValueFactory(new PropertyValueFactory<Services,String>("priceForCar"));
        CarColumn.setMaxWidth(100);

        TableColumn<Services,String> JeepColumn = new TableColumn<>("Τζιπ");
        JeepColumn.setCellValueFactory(new PropertyValueFactory<Services,String>("priceForJeep"));
        JeepColumn.setMaxWidth(100);

        TableColumn<Services,String> MotorBikeColumn = new TableColumn<>("Μηχανη");
        MotorBikeColumn.setCellValueFactory(new PropertyValueFactory<Services,String>("priceForMotorbike"));
        MotorBikeColumn.setMaxWidth(100);

        //Services
        ObservableList<Services> services = FXCollections.observableArrayList();
        services.add(new Services("Πλύσιμο εξωτερικό","7","8","6"));
        services.add(new Services("Πλύσιμο εσωτερικό","6","7","-"));
        services.add(new Services("Πλύσιμο εξωτ+εσωτ","12","14","-"));
        services.add(new Services("Πλύσιμο εξωτ.σπέσιαλ","9","10","8"));
        services.add(new Services("Πλύσιμο εσωτ.σπέσιαλ","8","9","-"));
        services.add(new Services("Πλύσιμο εξωτ+εσωτ + σπεσιαλ","15","17","-"));
        services.add(new Services("Βιολογικός καθαρισμός εσωτ","80","80","-"));
        services.add(new Services("Κέρωμα-Γυάλισμα","80","90","40"));
        services.add(new Services("Καθαρισμός κινητήρα","20","20","10"));
        services.add(new Services("Πλύσιμο σασΊ","3","3","-"));
        //Services

        table.setItems(services);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(servicesColumn,CarColumn,JeepColumn,MotorBikeColumn);

        pane.getChildren().add(table);

        Scene scene = new Scene(pane,1024,768);
        stage.setScene(scene);
        stage.show();
    }

}
