package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GameWindowController{

    private ResultsWindowController resultsController;

    @FXML
    private Label title;

    @FXML
    private TextField nameAnswer;

    @FXML
    private TextField animalAnswer;

    @FXML
    private TextField locationAnswer;

    @FXML
    private TextField objectAnswer;

    private Stage stage;

    public GameWindowController(ResultsWindowController r){
        resultsController = r;
    }

    public void setTitle(String text){
        title.setText("Letter: "+text);
    }

    @FXML
    private void stopBtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ResultsWindow.fxml"));
        try {
            Parent p = (Parent) loader.load();
            Scene scene = new Scene(p);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
