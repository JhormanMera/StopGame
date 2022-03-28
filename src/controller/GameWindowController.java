package controller;

import events.OnMessageSended;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private OnMessageSended sended;

    public GameWindowController(ResultsWindowController r){
        resultsController = r;
    }

    public void getOwnAnswers(){
        String msg= nameAnswer.getText().trim()+"//"+animalAnswer.getText().trim()+"//"+locationAnswer.getText().trim()+"//"+objectAnswer.getText().trim();
        sended.onMessageSended(msg);
    }

    public void setTitle(String text){
        title.setText("Letter: "+text);
    }

    public void setSended(OnMessageSended sended) {this.sended = sended;}

    @FXML
    private void stopBtn(ActionEvent event) {
        if(nameAnswer.getText().trim().isEmpty()||animalAnswer.getText().trim().isEmpty()||locationAnswer.getText().trim().isEmpty()||objectAnswer.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Try Again");
            alert.setContentText("All fields must be filled before attempting to stop the game");
            alert.showAndWait();
        }else{
            getOwnAnswers();
        }

    }

}
