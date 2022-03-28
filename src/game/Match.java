package game;

import com.google.gson.Gson;
import events.OnMessageSended;
import model.Letter;
import server.Session;

import java.io.IOException;
import java.util.Random;

public class Match {
    private Session Player1;
    private Session Player2;
    private boolean playAgain;
    private OnMessageSended sender1;
    private OnMessageSended sender2;


    public Match(Session p1, Session p2){
        Player1=p1;
        Player2=p2;
        playAgain=false;
        sender1=p1;
        sender2=p2;
    }

    public String calculatePoints(String results1, String results2){
        String points="";

        return points;
    }

    public void sendLetter(){
        Random random = new Random();
        String matchLetter = String.valueOf((char) (random.nextInt(26) + 'A'));
        Letter letter = new Letter(matchLetter);
        Gson gson = new Gson();
        String line = gson.toJson(letter);
        System.out.println("Letra: "+line);
        sender1.onMessageSended(line);
        sender2.onMessageSended(line);

    }
    public Session getPlayer1() {
        return Player1;
    }

    public Session getPlayer2() {
        return Player2;
    }

    public boolean isPlayAgain() {
        return playAgain;
    }

    public void setPlayAgain(boolean playAgain) {
        this.playAgain = playAgain;
    }

}
