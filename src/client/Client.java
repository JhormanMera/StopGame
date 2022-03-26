package client;

import controller.StopGameController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;


public class Client extends Thread {
    private StopGameController stopGame;

    public Client(){
        stopGame = new StopGameController();
    }

    public static void main(String[] args) {
       new Client().run();
    }

    @Override
    public void run() {
        new Thread(() -> {
            //Socket socket = new Socket("127.0.0.1", 6000);
            //Run on UI Thread
            System.out.println("GA");
            Platform.runLater(()->{
                try {
                    stopGame.openLoadWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }).run();

    }
}
