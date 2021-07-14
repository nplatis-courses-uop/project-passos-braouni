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

/**
 * JavaFX App
 */
public class Server extends Thread{
    private String complete ="";

    public boolean connected = false;
    private int ID = 0;

    @Override
    public void run() {
        Tameio io = new Tameio();
        try(ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("Started");
            String toPrint ="";
            int i = 1;
            while(true)
            {

                Socket client = serverSocket.accept();
                Scanner fromClient = new Scanner(client.getInputStream());
                String ta = fromClient.nextLine();
                Label x = new Label(ta);
                String in[] = null;
                System.out.println(ta);
                in = ta.split(" ");

                //in = fromClient.sp
                //System.out.println(Arrays.toString(in));
                System.out.println(in[0]);
                System.out.println(in[1]);
                this.complete = in[0];

                if (!this.complete.equals(""))
                {
//                    System.out.println(this.complete);
//                    this.complete = in[0];
//                    String value = in[1];

                    Label ba = new Label(in[0]);
                    Label be = new Label(in[1]);
                    Label aa = new Label(in[2]);
                    //io.obs.add(ba);

                    Runnable task = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Tameio.obs.add(x);
//                                        Tameio.cost.add(be);
//                                        Tameio.date.add(aa);
//                                        History.fileData.add(new Label("Hello"));
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    new Thread(task).start();
                    ID++;

//                    try(PrintWriter writer = new PrintWriter ("test.txt", StandardCharsets.UTF_8)) {
//                        writer.println("ID : " + ID + " Plate : " + in[0] + " Cost : " + in[1] + " Came At : " + in[2] + " Left at : " + "\n");
//                        writer.println();
//                        writer.close();
//                    }catch (FileNotFoundException e){
//                        e.printStackTrace();
//                    }
                    try {
                        File fl = new File("test.txt");
                        Scanner scanner = new Scanner(fl);
                        int line = 0;
                        int ID = 0;
                        while (scanner.hasNext())
                        {
                            //History.fileData.add(scanner.nextLine());

                            String[] out = scanner.nextLine().split(" :");
                            String t = out[1];


                            String[] s = t.split("");
                            ID = Integer.parseInt(s[1]);


                        }
                        ID++;
                        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));
                        out.println("ID : " + ID + ": Plate : " + in[0] + " Cost : " + in[1] + " Came At : " + in[2] + " Left at : " + " @@");


                        //History.readFile();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    in[0] = "";
                    in[1] = "";
                    in[2] = "";

                    complete = "";
                    Runnable task2 = new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(2000);
                                        History.readFile();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    };
                    new Thread(task2).start();

                }

//                try {
//                    Thread.sleep(3000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public Label retLabel(){


        return new Label(this.complete);
    }

    public void getConn() {
        if (!this.connected)
        {
            this.connected = true;
        }


    }

}

