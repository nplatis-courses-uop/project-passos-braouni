package gr.uop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import gr.uop.Client;
import gr.uop.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Catalogue extends Client
{
    private static CheckBox checkbox1, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6,checkbox7,checkbox8,checkbox9,checkbox10;
    private static RadioButton  car , jeep , motorbike ;
    private static int cost;
    private static TextField tfCost;
    private static String data = "";
    static Services EksPli;
    static Services EsPli;
    static Services EksEsPli;
    static Services EksPliSpecial;
    static Services EsPliSpecial;
    static Services EksEsPliSpecial;
    static Services EsBio;
    static Services wax;
    static Services engine;
    static Services chassis;
    private static Set<String> arr = new HashSet<>();
    private static ArrayList<Services> aw = new ArrayList<>();


    public static void start()
    {
        aw.clear();
        arr.clear();
        cost = 0;

        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        TableView table = new TableView<>();


        car = new RadioButton("Aμάξι");
        jeep = new RadioButton("Τζιπ");
        motorbike = new RadioButton("Mηχανη");

        tfCost = new TextField();

        ToggleGroup vehicle = new ToggleGroup();

        car.setToggleGroup(vehicle);
        jeep.setToggleGroup(vehicle);
        motorbike.setToggleGroup(vehicle);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(car,jeep,motorbike);
        hbox.setSpacing(55);
        hbox.setPadding(new Insets(10,0,0,210));
        hbox.setAlignment(Pos.TOP_LEFT);

        checkbox1 = new CheckBox();
        checkbox1.setOnAction(event -> processCheckBox(event));

        checkbox2 = new CheckBox();
        checkbox2.setOnAction(event -> processCheckBox(event));

        checkbox3 = new CheckBox();
        checkbox3.setOnAction(event -> processCheckBox(event));

        checkbox4 = new CheckBox();
        checkbox4.setOnAction(event -> processCheckBox(event));

        checkbox5 = new CheckBox();
        checkbox5.setOnAction(event -> processCheckBox(event));

        checkbox6 = new CheckBox();
        checkbox6.setOnAction(event -> processCheckBox(event));

        checkbox7 = new CheckBox();
        checkbox7.setOnAction(event -> processCheckBox(event));

        checkbox8 = new CheckBox();
        checkbox8.setOnAction(event -> processCheckBox(event));

        checkbox9 = new CheckBox();
        checkbox9.setOnAction(event -> processCheckBox(event));

        checkbox10 = new CheckBox();
        checkbox10.setOnAction(event -> processCheckBox(event));


        EksPli= new Services("Πλύσιμο_εξωτερικό","7","8","6",checkbox1);
        EsPli= new Services("Πλύσιμο_εσωτερικό","6","7","-",checkbox2);
        EksEsPli= new Services("Πλύσιμο_εξωτ+εσωτ","12","14","-",checkbox3);
        EksPliSpecial= new Services("Πλύσιμο_εξωτ.σπέσιαλ","9","10","8",checkbox4);
        EsPliSpecial= new Services("Πλύσιμο_εσωτ.σπέσιαλ","8","9","-",checkbox5);
        EksEsPliSpecial= new Services("Πλύσιμο_εξωτ+εσωτ_+_σπεσιαλ","15","17","-",checkbox6);
        EsBio= new Services("Βιολογικός_καθαρισμός_εσωτ","80","80","-",checkbox7);
        wax= new Services("Κέρωμα-Γυάλισμα","80","90","40",checkbox8);
        engine= new Services("Καθαρισμός_κινητήρα","20","20","10",checkbox9);
        chassis= new Services("Πλύσιμο_σασί","3","3","-",checkbox10);

        aw.add(EksPli);
        aw.add(EsPli );
        aw.add(EksEsPli );
        aw.add(EksPliSpecial );
        aw.add(EsPliSpecial );
        aw.add(EksEsPliSpecial );
        aw.add(EsBio );
        aw.add(wax );
        aw.add(engine );
        aw.add(chassis );

        ObservableList<Services> services = FXCollections.observableArrayList();
        services.add(EksPli);
        services.add(EsPli);
        services.add(EksEsPli);
        services.add(EksPliSpecial);
        services.add(EsPliSpecial);
        services.add(EksEsPliSpecial);
        services.add(EsBio);
        services.add(wax);
        services.add(engine);
        services.add(chassis);

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

        TableColumn<Services,String> CheckBoxColumn = new TableColumn<>("Επιλογή");
        CheckBoxColumn.setCellValueFactory(new PropertyValueFactory<Services,String>("checkBoxSelection"));
        CheckBoxColumn.setMaxWidth(100);

        //Services

        table.setItems(services);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(servicesColumn,CarColumn,JeepColumn,MotorBikeColumn,CheckBoxColumn);


        pane.setTop(table);
        pane.setCenter(hbox);

        Label totalCost = new Label("Συνολικο κοστος");
        totalCost.setPadding(new Insets(5, 5, 0, 0));

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(totalCost,tfCost);

        Button confirm = new Button("Confirm");
        confirm.setPadding(new Insets(13));
        Button cancel = new Button("Cancel ");
        cancel.setPadding(new Insets(13));

        confirm.setOnAction(event ->
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Επιβεβαίωση");
            alert.setContentText("Είστε σίγουρος ότι θέλετε να συνεχίσετε?");
            alert.setHeaderText(null);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(stage);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("OK");
                for (Services s : aw){

                    if (s.getCheckBoxSelection().isSelected()) {
                        arr.add(s.getService());
                    }

                }
                System.out.println(serviceReturn());
                stage.close();
            }
            else if (result.get() == ButtonType.CANCEL) {
                System.out.println("Cancel");
            }
        });

        cancel.setOnAction(event ->
        {
            stage.close();
        });

        table.requestFocus();

        HBox hbox3 = new HBox(8);
        hbox3.getChildren().addAll(confirm,cancel);

        hbox3.setPadding(new Insets(0, 0, 280, 430));

        pane.setRight(hbox2);
        pane.setBottom(hbox3);

        Scene scene = new Scene(pane,1024,768);
        stage.setScene(scene);
        stage.show();
    }

    private static void processCheckBox(ActionEvent event)
    {
        if(event.getSource()==checkbox1)
        {
            if(checkbox1.isSelected())
            {
                checkbox3.setSelected(false);
                checkbox4.setSelected(false);
                checkbox6.setSelected(false);

                calculateCost(7,8,6);
            }
            else
                calculateCost(-7,-8,-6);
            //cost = cost + 0.50;
        }
        else if(event.getSource()==checkbox2)
        {
            if(checkbox2.isSelected())
            {
                checkbox3.setSelected(false);
                checkbox5.setSelected(false);
                checkbox6.setSelected(false);
                checkbox7.setSelected(false);

                calculateCost(6,7,0);
            }
            else
                calculateCost(-6,-7,0);
        }
        else if(event.getSource()==checkbox3)
        {
            if(checkbox3.isSelected())
            {
                if(checkbox1.isSelected())
                    calculateCost(-7,-8,-6);
                if(checkbox2.isSelected())
                    calculateCost(-6,-7,0);
                if(checkbox4.isSelected())
                    calculateCost(-9,-10,-8);
                if(checkbox5.isSelected())
                    calculateCost(-8,-9,0);
                if(checkbox6.isSelected())
                    calculateCost(-15,-17,0);
                if(checkbox7.isSelected())
                    calculateCost(-80,-80,0);
                checkbox1.setSelected(false);
                checkbox2.setSelected(false);
                checkbox4.setSelected(false);
                checkbox5.setSelected(false);
                checkbox6.setSelected(false);
                checkbox7.setSelected(false);


                calculateCost(12,14,0);
            }
            else
                calculateCost(-12,-14,0);
        }
        else if(event.getSource()==checkbox4)
        {
            if(checkbox4.isSelected())
            {
                if(checkbox1.isSelected())
                    calculateCost(-7,-8,-6);
                if(checkbox3.isSelected())
                    calculateCost(-12,-14,0);
                checkbox1.setSelected(false);
                checkbox3.setSelected(false);

                calculateCost(9,10,8);
            }
            else
                calculateCost(-9,-10,-8);
        }
        else if(event.getSource()==checkbox5)
        {
            if(checkbox5.isSelected())
            {
                if(checkbox2.isSelected())
                    calculateCost(-6,-7,0);
                if(checkbox3.isSelected())
                    calculateCost(-12,-14,0);
                if(checkbox6.isSelected())
                    calculateCost(-15,-17,0);
                if(checkbox7.isSelected())
                    calculateCost(-80,-80,0);
                checkbox2.setSelected(false);
                checkbox3.setSelected(false);
                checkbox6.setSelected(false);
                checkbox7.setSelected(false);

                calculateCost(8,9,0);
            }
            else
                calculateCost(-8,-9,0);
        }
        else if(event.getSource()==checkbox6)
        {
            if(checkbox6.isSelected())
            {
                if(checkbox1.isSelected())
                    calculateCost(-7,-8,-6);
                if(checkbox2.isSelected())
                    calculateCost(-6,-7,0);
                if(checkbox3.isSelected())
                    calculateCost(-12,-14,0);
                if(checkbox4.isSelected())
                    calculateCost(-9,-10,-8);
                if(checkbox5.isSelected())
                    calculateCost(-8,-9,0);
                if(checkbox7.isSelected())
                    calculateCost(-80,-80,0);
                checkbox1.setSelected(false);
                checkbox2.setSelected(false);
                checkbox3.setSelected(false);
                checkbox4.setSelected(false);
                checkbox5.setSelected(false);
                checkbox7.setSelected(false);

                calculateCost(15,17,0);
            }
            else
                calculateCost(-15,-17,0);
        }
        else if(event.getSource()==checkbox7)
        {
            if(checkbox7.isSelected())
            {
                if(checkbox2.isSelected())
                    calculateCost(-6,-7,0);
                if(checkbox3.isSelected())
                    calculateCost(-12,-14,0);
                if(checkbox5.isSelected())
                    calculateCost(-8,-9,0);
                if(checkbox6.isSelected())
                    calculateCost(-15,-17,0);
                checkbox2.setSelected(false);
                checkbox3.setSelected(false);
                checkbox5.setSelected(false);
                checkbox6.setSelected(false);

                calculateCost(80,80,0);
            }
            else
                calculateCost(-80,-80,0);
        }
        else if(event.getSource()==checkbox8)
        {
            if(checkbox8.isSelected())
                calculateCost(80,90,40);
            else
                calculateCost(-80,-90,-40);
        }
        else if(event.getSource()==checkbox9)
        {
            if(checkbox9.isSelected())
                calculateCost(20,20,10);
            else
                calculateCost(-20,-20,-10);
        }
        else if(event.getSource()==checkbox10)
        {
            if(checkbox10.isSelected())
                calculateCost(3,3,0);
            else
                calculateCost(-3,-3,0);
        }
    }

    private static void calculateCost(Integer costA , Integer CostB , Integer CostC)
    {
        if(car.isSelected())
            cost += costA;
        else if(jeep.isSelected())

            cost += CostB;
        else
            cost += CostC;
        tfCost.setText(""+cost);

    }

    public static int getCost() {
        return cost;
    }

    public static String serviceReturn(){
        String forReturn ="";
        for (String s : arr){
            forReturn+=s+"_";
        }
        return forReturn;
    }



}