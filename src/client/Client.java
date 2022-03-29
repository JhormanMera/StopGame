package client;


import com.google.gson.Gson;
import controller.GameWindowController;
import controller.ResultsWindowController;
import events.OnMessageSended;
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
import java.util.Arrays;
import java.util.List;

public class Client extends Application implements OnMessageSended {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 6000;
    private static Socket socket;
    private static BufferedWriter writer;
    private static BufferedReader reader;
    private final GameWindowController gameController;
    private final ResultsWindowController resultsController;


    public static void main(String[] args) {
        new Client();
        launch(args);
    }

    public Client() {
        resultsController = new ResultsWindowController();
        gameController = new GameWindowController(resultsController);
        gameController.setSended(this);
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

            //PONER AL CLIENTE A ESCUCHAR
            //ABRE SEGUNDA VENTANA
            readMessage(primaryStage);


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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                if(msg.startsWith("{")){
                    Generic generic = gson.fromJson(msg,Generic.class);
                            switch(generic.type){
                                case "Letter":
                                    Letter letter = gson.fromJson(msg,Letter.class);
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
                                        new Thread(()->{
                                            readMessage(stage);
                                        }).start();
                                    });
                                    break;
                                case "Msg":
                                    gameController.getOwnAnswers();
                                    Platform.runLater(()->{
                                        new Thread(()->{
                                            readMessage(stage);
                                        }).start();
                                    });
                                    break;
                            }

                }else if(msg.startsWith("[")){
                    Result[] result = gson.fromJson(msg, Result[].class);
                    List<Result> alfa = Arrays.asList(result);
                    Platform.runLater(()->{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ResultsWindow.fxml"));
                        try {
                            loader.setController(resultsController);
                            Parent p =loader.load();
                            Scene scene = new Scene(p);
                            stage.setScene(scene);
                            //PONGO LOS RESULTADOS EN LA SIGUIENTE VENTANA
                            setResultsOnWindow(alfa);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }).start();
    }

    private void setResultsOnWindow(List<Result> alfa){
        Result r=alfa.get(0);
        resultsController.setOwnResults(r.getName(),r.getAnimal(),r.getPlace(),r.getObject());
        resultsController.setOwnPoints(r.getNamePoints(),r.getAnimalPoints(),r.getPlacePoints(),r.getObjectPoints(),r.getTotalPoints());
        r=alfa.get(1);
        resultsController.setOpponentResults(r.getName(),r.getAnimal(),r.getPlace(),r.getObject());
        resultsController.setOpponentPoints(r.getNamePoints(),r.getAnimalPoints(),r.getPlacePoints(),r.getObjectPoints(),r.getTotalPoints());
    }

    @Override
    public void onMessageSended(String sended) {
        Gson gson = new Gson();
        String[] msg = sended.split("//");
        Result r = new Result(msg[0],msg[1], msg[2],msg[3]);
        String send=gson.toJson(r);
        try {
            writer.write(send+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
