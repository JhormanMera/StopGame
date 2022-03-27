package client;


import controller.StopGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application {
    private static String IP = "127.0.0.1";
    private static int PORT = 6000;
    private static Socket socket;
    private static BufferedWriter bwriter;
    private static BufferedReader breader;
    private StopGameController stopController;


    public static void main(String[] args) {
        new Client();
        launch(args);
    }

    public Client() {
        stopController = new StopGameController();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            socket = new Socket(IP,PORT);
            System.out.println("Conectado");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/LoadWindow.fxml"));
            loader.setController(stopController);
            Parent p = (Parent) loader.load();
            Scene scene = new Scene(p);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(){

    }
}
