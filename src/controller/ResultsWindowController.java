package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultsWindowController implements Initializable {

    private String ownResults;

    private String opponentResults;

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

    public ResultsWindowController() {  }

    @FXML
    void finishBtn(ActionEvent event) {

    }

    @FXML
    void playAgainBtn(ActionEvent event) {

    }

    public void setOwnResults(String ownResults) {
        this.ownResults = ownResults;
    }

    public void setOpponentResults(String opponentResults) {
        this.opponentResults = opponentResults;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

