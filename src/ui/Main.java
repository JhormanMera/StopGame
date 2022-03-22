package ui;

import controller.LoadingWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class  Main extends Application {
    private LoadingWindowController loadController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadController = new LoadingWindowController();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("LoadingWindow.fxml"));
        fxmlloader.setController(loadController);
        Parent root = (Parent) fxmlloader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
