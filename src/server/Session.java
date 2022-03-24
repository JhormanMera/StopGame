package server;

import java.io.*;
import java.net.Socket;

public class Session extends Thread{
    private Socket socket;

    private BufferedReader reader;
    private BufferedWriter writer;

    private OnMessageListener listener;

    public Session(Socket socket) {
        this.socket = socket;

    }
    @Override
    public void run() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true) {
                /*
                String line = reader.readLine();
                System.out.println(line);
                listener.onMessage(line);*/
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setListener(OnMessageListener listener) {
        this.listener = listener;
    }

    public interface OnMessageListener{
        void onMessage(String line);
    }
}
