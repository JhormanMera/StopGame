package controller;

public class StopGameController {
    private GameWindowController gameController;
    private ResultsWindowController resultsController;

    public StopGameController() {
        gameController = new GameWindowController();
        resultsController = new ResultsWindowController();
    }

}
