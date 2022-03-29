package game;

import com.google.gson.Gson;
import events.OnMessageReceived;
import events.OnMessageSended;
import model.Letter;
import model.Message;
import model.Result;
import server.Session;

import java.util.ArrayList;
import java.util.Random;

public class Match {
    private final static String REPEATED_ANSWER="50";
    private final static String NON_REPEATED_ANSWER="100";
    private final static String NON_ANSWERED="0";
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

    public void calculatePoints(Result answers1, Result answers2){
        //Nombre
        String[] strings =calculatePoints(answers1.getName().toUpperCase(),answers2.getName().toUpperCase());
        answers1.setNamePoints(strings[0]);
        answers2.setNamePoints(strings[1]);
        //Animal
        strings= calculatePoints(answers1.getAnimal().toUpperCase(),answers2.getAnimal().toUpperCase());
        answers1.setAnimalPoints(strings[0]);
        answers2.setAnimalPoints(strings[1]);
        //Lugar
        strings = calculatePoints(answers1.getPlace().toUpperCase(),answers2.getPlace().toUpperCase());
        answers1.setPlacePoints(strings[0]);
        answers2.setPlacePoints(strings[1]);
        //Objeto
        strings=calculatePoints(answers1.getObject().toUpperCase(),answers2.getObject().toUpperCase());
        answers1.setObjectPoints(strings[0]);
        answers2.setObjectPoints(strings[1]);
        //Total
        answers1.setTotalPoints();
        answers2.setTotalPoints();

    }
    private String[] calculatePoints(String ans1, String ans2){
        String[] strings = new String[2];
        if(ans2.equalsIgnoreCase("NONE")&&ans1.equalsIgnoreCase("NONE")) {
            strings[0] = NON_ANSWERED;
            strings[1] = NON_ANSWERED;
        }else if(ans1.equalsIgnoreCase("NONE")) {
            strings[0] = NON_ANSWERED;
            strings[1] = NON_REPEATED_ANSWER;
        }else if(ans2.equalsIgnoreCase("NONE")){
            strings[0]=NON_REPEATED_ANSWER;
            strings[1]=NON_ANSWERED;
        }else if(ans1.equalsIgnoreCase(ans2)){
            strings[0]=REPEATED_ANSWER;
            strings[1]=REPEATED_ANSWER;
        }else{
            strings[0]=NON_REPEATED_ANSWER;
            strings[1]=NON_REPEATED_ANSWER;
        }
        return strings;
    }
    public void changeOrder(ArrayList<Result> ans){
        Result r=ans.get(0);
        Result ro=ans.get(1);
        ans.clear();
        ans.add(ro);
        ans.add(r);
    }

    public void sendLetter(){
        Random random = new Random();
        String matchLetter = String.valueOf((char) (random.nextInt(26) + 'A'));
        Letter letter = new Letter(matchLetter);
        Gson gson = new Gson();
        String line = gson.toJson(letter);
        sender1.onMessageSended(line);
        sender2.onMessageSended(line);
        readMessage();
    }
    public void readMessage(){

        P1Thread = new Thread() {

            public void run() {

                synchronized(this) {
                    //Recibe las respuéstas del jugador que presionó stop
                    String a = receiver1.onMessageReceived();
                    if(a.contains("Result")) {
                        //Detiene el hilo del otro jugador
                        P2Thread.interrupt();
                    }
                    //Envía "Answers" como código para que se retornen las respuestas del otro jugador
                    Message msg = new Message("Answers");
                    Gson gson = new Gson();
                    String line = gson.toJson(msg);
                    sender2.onMessageSended(line);
                    //***
                    //Guarda las respuestas del segundo jugador
                    String b = receiver2.onMessageReceived();
                    //Deserializa el Json y calcula puntajes
                    Result own = gson.fromJson(a,Result.class);
                    Result opp = gson.fromJson(b,Result.class);
                    ArrayList<Result> answers = new ArrayList<>();
                    answers.add(own); answers.add(opp);
                    calculatePoints(answers.get(0),answers.get(1));
                    String send = gson.toJson(answers);
                    sender1.onMessageSended(send);
                    changeOrder(answers);
                    send = gson.toJson(answers);
                    sender2.onMessageSended(send);
                }
            }
        };

        P2Thread = new Thread() {

            public void run() {

                synchronized(this) {
                    //Recibe las respuéstas del jugador que presionó stop
                    String a = receiver2.onMessageReceived();
                    if(a.contains("Result")) {
                        //Detiene el hilo del otro jugador
                        P1Thread.interrupt();
                    }
                    //Envía "Answers" como código para que se retornen las respuestas del otro jugador
                    Message msg = new Message("Answers");
                    Gson gson = new Gson();
                    String line = gson.toJson(msg);
                    sender1.onMessageSended(line);
                    //***
                    //Guarda las respuestas del segundo jugador
                    String b = receiver1.onMessageReceived();
                    //Deserializa el Json y calcula puntajes
                    Result own = gson.fromJson(a,Result.class);
                    Result opp = gson.fromJson(b,Result.class);
                    ArrayList<Result> answers = new ArrayList<>();
                    answers.add(own); answers.add(opp);
                    calculatePoints(answers.get(0),answers.get(1));
                    String send = gson.toJson(answers);
                    sender2.onMessageSended(send);
                    changeOrder(answers);
                    send = gson.toJson(answers);
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
