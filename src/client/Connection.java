package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Connection extends Thread {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 6000;

    private Socket socket;
    private BufferedWriter bwriter;

    public Connection() { }

    @Override
    public void run() {
        try {
            socket = new Socket(IP, PORT);
            OutputStream os = socket.getOutputStream();
            bwriter = new BufferedWriter(new OutputStreamWriter(os));
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public void sendMessage(String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bwriter.write(msg + "\n");
                    bwriter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }).start();

    }

}
