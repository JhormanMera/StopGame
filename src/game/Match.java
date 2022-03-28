package game;

import com.google.gson.Gson;
import events.OnMessageReceived;
import events.OnMessageSended;
import model.Letter;
import model.Message;
import server.Session;

import java.io.IOException;
import java.util.ArrayList;
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
                    if(a.contains("Result")) {
                        P2Thread.interrupt();
                    }
                    Message msg = new Message ("Answers");
                    Gson gson = new Gson();
                    String line = gson.toJson(msg);
                    sender2.onMessageSended(line);
                    String b = receiver2.onMessageReceived();
                    ArrayList<String> answers = new ArrayList<>();
                    answers.add(a);
                    answers.add(b);
                    String send = gson.toJson(answers);
                    sender1.onMessageSended(send);
                    answers.clear();
                    answers.add(b);
                    answers.add(a);
                    sender2.onMessageSended(send);

                }
            }
        };


        P2Thread = new Thread() {

            public void run() {

                synchronized(this) {

                    String a = receiver2.onMessageReceived();
                    if(a.contains("Result")) {
                        P1Thread.interrupt();
                    }
                    Message msg = new Message("Answers");
                    Gson gson = new Gson();
                    String line = gson.toJson(msg);
                    sender1.onMessageSended(line);
                    String b = receiver1.onMessageReceived();
                    ArrayList<String> answers = new ArrayList<>();
                    answers.add(a);
                    answers.add(b);
                    String send = gson.toJson(answers);
                    sender2.onMessageSended(send);
                    answers.clear();
                    answers.add(b);
                    answers.add(a);
                    sender1.onMessageSended(send);
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
