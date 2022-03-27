package client;


import controller.StopGameController;

import java.io.*;
import java.net.Socket;

public class Client{
    private static String IP="127.0.0.1";
    private static int PORT=6000;
    private static Socket socket;
    private static BufferedWriter bwriter;
    private static BufferedReader breader;
    private StopGameController stopController;

    public Client(){
        stopController = new StopGameController();
        while(true) {
            clientConnection();
        }
    }

    public static void main(String[] args) {
        new Client();
    }

    private void clientConnection() {
        try {
            socket = new Socket(IP,PORT);
            System.out.println("Conectado");
            stopController.openLoadWindow();
            bwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            breader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = breader.readLine();
            bwriter.write(line+"/n");
            bwriter.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
