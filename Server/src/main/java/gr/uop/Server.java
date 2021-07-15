package gr.uop;

import gr.uop.Tameio;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Server extends Thread {
    private String complete = "";

    public boolean connected = false;
    private int ID = 0;

    @Override
    public void run() {
        Tameio io = new Tameio();
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("Started");
            String toPrint = "";
            int i = 1;
            while (true) {

                Socket client = serverSocket.accept();
                Scanner fromClient = new Scanner(client.getInputStream());

                while (fromClient.hasNextLine()) {
                    String ta = fromClient.nextLine();
                    ta += " @@";
                    Label x = new Label(ta);

                    System.out.println(ta);

                    this.complete = ta;

                    if (!this.complete.equals("")) {


//
                        Tameio.obs.add(complete);
//
//


                        try {
                            File fl = new File("test.txt");
                            Scanner scanner = new Scanner(fl);


                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));

                            out.println(ta);


                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        complete = "";


                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Label retLabel() {


        return new Label(this.complete);
    }

    public void getConn() {
        if (!this.connected) {
            this.connected = true;
        }


    }

}











