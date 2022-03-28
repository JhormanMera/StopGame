package server;

import game.StopGame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ArrayList<Session> sessions;
    private StopGame stopGame;

    public static void main(String[] args) throws IOException {
        new Server();
    }

    public Server() throws IOException {
        stopGame = StopGame.getInstance();
        sessions = new ArrayList<>();
        ServerSocket server = new ServerSocket(6000);
        while (true) {
            System.out.println("Esperando cliente...");
            Socket socket = server.accept();
            System.out.println("Nuevo cliente conectado!");
            System.out.println("Entró en el puerto: " + socket.getPort());
            Session session = new Session(socket);
            session.start();
            sessions.add(session);
            System.out.println("size: "+sessions.size());
            if(sessions.size()%2==0){
                System.out.println("Entra IF");
                stopGame.addMatch(sessions.get(sessions.size()-1),sessions.get(sessions.size()-2));
            }
        }
    }
}
