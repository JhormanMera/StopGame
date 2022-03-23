package client;

import controller.LoadingWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    private LoadingWindowController loadController;
    private Connection connection;

    public static void main(String[] args) {
        new Client();
        launch(args);
    }

    public Client() {
        loadController = new LoadingWindowController();
        connection = new Connection();
        connection.start();
        init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/ui/LoadingWindow.fxml"));
        fxmlloader.setController(loadController);
        try {
            Parent root = (Parent) fxmlloader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        while (true) {
            //String line = scanner.nextLine();
            //Envio
            connection.sendMessage("line");
        }
    }
}
