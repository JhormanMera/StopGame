package server;

import events.OnMessageReceived;
import events.OnMessageSended;

import java.io.*;
import java.net.Socket;

public class Session extends Thread implements OnMessageSended, OnMessageReceived {
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

    @Override
    public void onMessageSended(String sended) {
        System.out.println("Enviando: "+sended);
        try {
            writer.write(sended+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String onMessageReceived() {
        String message="";
            try {
               message = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return message;
    }


}
