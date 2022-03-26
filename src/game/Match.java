package game;

import server.Session;

public class Match {
    private Session Player1;
    private Session Player2;
    private int p1Points;
    private int p2Points;


    public Match(Session p1, Session p2){
        Player1=p1;
        Player2=p2;
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
}
