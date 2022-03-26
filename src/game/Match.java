package game;

import server.Session;

import java.util.Random;

public class Match {
    private Session Player1;
    private Session Player2;
    private String p1Answers;
    private String p2Answers;
    private int p1Points;
    private int p2Points;
    private boolean playAgain;
    private char matchLetter;



    public Match(Session p1, Session p2){
        Player1=p1;
        Player2=p2;
        playAgain=false;
    }
    public void sendLetter(){
        Random random = new Random();
        matchLetter = (char) (random.nextInt(26) + 'A');

    }
    public Session getPlayer1() {
        return Player1;
    }

    public void setPlayer1(Session player1) {
        Player1 = player1;
    }

    public Session getPlayer2() {
        return Player2;
    }

    public void setPlayer2(Session player2) {
        Player2 = player2;
    }

    public String getP1Answers() {
        return p1Answers;
    }

    public void setP1Answers(String p1Answers) {
        this.p1Answers = p1Answers;
    }

    public String getP2Answers() {
        return p2Answers;
    }

    public void setP2Answers(String p2Answers) {
        this.p2Answers = p2Answers;
    }

    public int getP1Points() {
        return p1Points;
    }

    public void setP1Points(int p1Points) {
        this.p1Points = p1Points;
    }

    public int getP2Points() {
        return p2Points;
    }

    public void setP2Points(int p2Points) {
        this.p2Points = p2Points;
    }

    public boolean isPlayAgain() {
        return playAgain;
    }

    public void setPlayAgain(boolean playAgain) {
        this.playAgain = playAgain;
    }
}
