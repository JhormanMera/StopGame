package server;

import java.io.*;
import java.net.Socket;

public class Session extends Thread{
    private Socket socket;

    private BufferedReader reader;
    private BufferedWriter writer;

    public Session(Socket socket) {
        this.socket = socket;

    }
    @Override
    public void run() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
