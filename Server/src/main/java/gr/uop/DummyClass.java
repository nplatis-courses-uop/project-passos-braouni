package gr.uop;

import javafx.scene.control.Label;

public class DummyClass {

    private String first;
    private String second;
    private String third;
    private String fourth;
    private String fifth;
    public DummyClass(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public DummyClass(String first, String second, String third, String fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;

    }


    public DummyClass(String first, String second, String third, String fourth,String fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }


    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    @Override
    public String toString() {
        return first + " " + second + " " + third + " " + fourth;
    }


}
