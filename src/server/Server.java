package server;

import game.Match;
import game.StopGame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException {
        new Server();
    }
    private ArrayList<Session> sessions;
    private StopGame stopGame;

    public Server() throws IOException {
        stopGame = new StopGame();
        sessions = new ArrayList<>();
        ServerSocket server = new ServerSocket(6000);
        while (true) {
            System.out.println("Esperando cliente...");
            Socket socket = server.accept();
            System.out.println("Nuevo cliente conectado!");
            System.out.println("Entró en el puerto: " + socket.getPort());
            Session session = new Session(socket);
            sessions.add(session);
            if(sessions.size()%2==0){
                Match match = new Match(sessions.get(sessions.size()-1),sessions.get(sessions.size()-2));
                stopGame.addMatch(match);
                match.sendLetter();
            }
        }
    }
}
