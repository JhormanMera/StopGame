package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StopGameController {
    private GameWindowController gameController;
    private ResultsWindowController resultsController;

    public StopGameController() {
        gameController = new GameWindowController();
        resultsController = new ResultsWindowController();
    }

    public void openLoadWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/LoadingWindow.fxml"));
        loader.setController(this);
        Parent p = (Parent) loader.load();
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
