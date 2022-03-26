package game;

import server.Session;

import java.util.ArrayList;

public class StopGame {
    private ArrayList<Match> matches;
    private final static int REPEATED_ANSWER=50;
    private final static int NON_REPEATED_ANSWER=100;
    private final static int NON_ANSWERED=0;

    private static StopGame instance;

    public StopGame(){
        matches = new ArrayList<>();
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void addMatch(Match match){
        matches.add(match);
    }

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


    public String calculatePoints(String results1, String results2){
        String points="";


        return points;
    }
}
