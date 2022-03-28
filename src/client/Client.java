package client;


import com.google.gson.Gson;
import controller.GameWindowController;
import controller.ResultsWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Generic;
import model.Letter;
import model.Message;
import model.Result;

import java.io.*;
import java.net.Socket;

public class Client extends Application {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 6000;
    private static Socket socket;
    private static BufferedWriter writer;
    private static BufferedReader reader;
    private final GameWindowController gameController;
    private final ResultsWindowController resultsController;
    private Letter letter;

    public static void main(String[] args) {
        new Client();
        launch(args);
    }

    public Client() {
        resultsController = new ResultsWindowController();
        gameController = new GameWindowController(resultsController);
    }

    @Override
    public void start(Stage primaryStage){
        try {
            socket = new Socket(IP,PORT);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Conectado");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/LoadWindow.fxml"));
            try {
                Parent p = loader.load();
                Scene scene = new Scene(p);
                primaryStage.setScene(scene);
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

            readMessage(primaryStage);
            //PONER AL CLIENTE A ESCUCHAR
            //ABRE SEGUNDA VENTANA

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage(Stage stage){
            new Thread(()->{
                String msg = "";
                try {
                    msg= msg.trim();
                    while(msg.isEmpty()){
                        msg = reader.readLine();
                    }
                    System.out.println("Mensaje: "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                Generic generic = gson.fromJson(msg,Generic.class);

                switch (generic.type){
                    case "Letter":
                        letter = gson.fromJson(msg,Letter.class);
                        Platform.runLater(()->{

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/GameWindow.fxml"));
                            try {
                                loader.setController(gameController);
                                Parent p = loader.load();
                                Scene scene = new Scene(p);
                                stage.setScene(scene);
                                gameController.setTitle(letter.getLetter());
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        break;
                    case "Message":
                        Message message = gson.fromJson(msg,Message.class);
                        break;
                    case "Result":
                        Result result = gson.fromJson(msg, Result.class);
                        break;

                }
            }).start();

    }

    public void sendMessage(String message) throws IOException {
        writer.write(message+"\n");
    }
}
