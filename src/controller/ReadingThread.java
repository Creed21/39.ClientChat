package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadingThread extends Thread {
    private Socket socket;
    private BufferedReader serverReaderInputStream;

    public ReadingThread(Socket socket) {
        this.socket = socket;
        try {
            serverReaderInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readMsgFromServer() {
        try {
            return serverReaderInputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        while(true) {
            String msgFromServer = readMsgFromServer();
            System.out.println("Server: " + msgFromServer);
        }
    }
}
