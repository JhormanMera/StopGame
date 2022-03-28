package game;

import com.google.gson.Gson;
import events.OnMessageReceived;
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
    private OnMessageReceived receiver1;
    private OnMessageReceived receiver2;
    private Thread P1Thread;
    private Thread P2Thread;


    public Match(Session p1, Session p2){
        Player1=p1;sender1=p1;receiver1=p1;
        Player2=p2; sender2=p2;receiver2=p2;
        playAgain=false;
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
        readMessage();
    }
    public void readMessage(){
        P1Thread = new Thread() {
            public void run() {
                synchronized(this) {
                    String a = receiver1.onMessageReceived();

                    if(a.contains("Answer")) {

                        P2Thread.interrupt();

                    }


                    sender1.onMessageSended(a);
                    String b = receiver2.onMessageReceived();
                    sender1.onMessageSended(b);

                }
            }
        };


        P2Thread = new Thread() {

            public void run() {

                synchronized(this) {

                    String b = receiver2.onMessageReceived();

                    if(b.contains("Answer")) {

                        P1Thread.interrupt();

                    }

                    sender1.onMessageSended(b);

                    String a = receiver1.onMessageReceived();

                    sender2.onMessageSended(a);

                }

            }
        };
        P1Thread.start();
        P2Thread.start();
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
