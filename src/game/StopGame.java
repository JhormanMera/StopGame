package game;

import server.Session;

import java.util.ArrayList;

public class StopGame {
    private ArrayList<Match> matches;
    private final static int REPEATED_ANSWER=50;
    private final static int NON_REPEATED_ANSWER=100;
    private final static int NON_ANSWERED=0;
    private static StopGame instance;

    private StopGame(){
        matches = new ArrayList<>();
    }

    public static StopGame getInstance(){
        if (instance==null){
            instance = new StopGame();
        }
        return instance;
    }
    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void addMatch(Session p1, Session p2){
        Match match = new Match(p1,p2);
        matches.add(match);
        match.sendLetter();
    }

    /*
    public void eraseMatch(Match match){
        matches.remove(match);
    }

    public void eraseMatch(Session player){
        boolean flag=true;
        for (int i = 0; i < matches.size()-1 && flag; i++) {
            if(matches.get(i).getPlayer1().equals(player) || matches.get(i).getPlayer2().equals(player)){
                matches.remove(i);
                flag=false;
            }
        }
    }
*/
}
