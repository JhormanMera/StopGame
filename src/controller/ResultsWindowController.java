package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultsWindowController{

    @FXML
    private Label ownNameResult;

    @FXML
    private Label opponentNameResult;

    @FXML
    private Label ownAnimalResult;

    @FXML
    private Label opponentAnimalResult;

    @FXML
    private Label ownLocationResult;

    @FXML
    private Label opponentLocationResult;

    @FXML
    private Label ownObjectResult;

    @FXML
    private Label opponentObjectResult;

    @FXML
    private Label ownNamepPoints;

    @FXML
    private Label ownAnimalPoints;

    @FXML
    private Label ownLocationPoints;

    @FXML
    private Label ownObjectPoints;

    @FXML
    private Label ownTotalPoints;

    @FXML
    private Label opponentTotalPoints;

    @FXML
    private Label opponentNamePoints;

    @FXML
    private Label opponentAnimalPoints;

    @FXML
    private Label opponentLocationPoints;

    @FXML
    private Label opponentObjectPoints;

    public ResultsWindowController() {  }


    @FXML
    void finishBtn(ActionEvent event) {
        Platform.exit();
    }

    public void setOwnResults(String name, String animal, String place, String object) {
        ownNameResult.setText(name);
        ownAnimalResult.setText(animal);
        ownLocationResult.setText(place);
        ownObjectResult.setText(object);
    }
    public void setOwnPoints(String namePoints, String animalPoints, String placePoints, String objectPoints, String totalPoints){
        ownNamepPoints.setText(namePoints);
        ownAnimalPoints.setText(animalPoints);
        ownLocationPoints.setText(placePoints);
        ownObjectPoints.setText(objectPoints);
        ownTotalPoints.setText(totalPoints);
    }

    public void setOpponentResults(String name, String animal, String place, String object) {
        opponentNameResult.setText(name);
        opponentAnimalResult.setText(animal);
        opponentLocationResult.setText(place);
        opponentObjectResult.setText(object);
    }

    public void setOpponentPoints(String namePoints, String animalPoints, String placePoints, String objectPoints, String totalPoints){
        opponentNamePoints.setText(namePoints);
        opponentAnimalPoints.setText(animalPoints);
        opponentLocationPoints.setText(placePoints);
        opponentObjectPoints.setText(objectPoints);
        opponentTotalPoints.setText(totalPoints);
    }

}

