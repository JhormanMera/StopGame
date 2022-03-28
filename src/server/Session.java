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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() throws IOException {
       String msg = reader.readLine();
    }

    public void sendMessage(String message) throws IOException {
        System.out.println("Enviando: "+message);
        writer.write(message+"\n");
        writer.flush();
    }
}
