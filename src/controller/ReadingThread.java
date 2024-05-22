package controller;

import gui.ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadingThread extends Thread {
    private Socket socket;
    private BufferedReader serverReaderInputStream;
    private ChatApp chatApp;

    public ReadingThread(Socket socket, ChatApp chatApp) {
        this.socket = socket;
        this.chatApp = chatApp;
        try {
            serverReaderInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readMsgFromServer() {
        try {
            String msg = serverReaderInputStream.readLine();
            chatApp.appendMessage(msg);
            return msg;
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
            if(msgFromServer == null) {
                System.out.println("connection broken");
                break;
            }
        }
    }
}
