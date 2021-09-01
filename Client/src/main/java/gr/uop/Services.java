package gr.uop;

import javafx.scene.control.CheckBox;

public class Services
{
    private String service;
    private String priceForCar;
    private String priceForJeep;
    private String priceForMotorbike;
    private CheckBox checkBoxSelection;

    public Services(String service , String priceForCar , String priceForJeep ,String priceForMotorbike , CheckBox checkBoxSelection)
    {
        this.service = service;
        this.priceForCar = priceForCar;
        this.priceForJeep = priceForJeep;
        this.priceForMotorbike = priceForMotorbike;
        this.checkBoxSelection = checkBoxSelection;
    }

    public CheckBox getCheckBoxSelection() {
        return checkBoxSelection;
    }

    public void setCheckBoxSelection(CheckBox checkBoxSelection) {
        this.checkBoxSelection = checkBoxSelection;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPriceForCar() {
        return priceForCar;
    }

    public void setPriceForCar(String priceForCar) {
        this.priceForCar = priceForCar;
    }

    public String getPriceForJeep() {
        return priceForJeep;
    }

    public void setPriceForJeep(String priceForJeep) {
        this.priceForJeep = priceForJeep;
    }

    public String getPriceForMotorbike() {
        return priceForMotorbike;
    }

    public void setPriceForMotorbike(String priceForMotorbike) {
        this.priceForMotorbike = priceForMotorbike;
    }

    public void test (CheckBox check){
        System.out.println(this.service + " "+  this.priceForCar + " "+ this.priceForJeep + " "+ this.priceForMotorbike);
    }

}
