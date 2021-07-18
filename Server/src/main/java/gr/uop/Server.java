package gr.uop;

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




    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            

            int i = 1;
            while (true) {

                Socket client = serverSocket.accept();
                Scanner fromClient = new Scanner(client.getInputStream());

                while (fromClient.hasNextLine()) {
                    String clientString = fromClient.nextLine();
                    clientString += " @@";
                    Label x = new Label(clientString);

                    

                    this.complete = clientString;

                    if (!this.complete.equals("")) {


//
                        Tameio.obs.add(complete);
//
//


                        try {
                            File fl = new File("test.txt");
                            Scanner scanner = new Scanner(fl);


                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));

                            out.println(clientString);


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



}











