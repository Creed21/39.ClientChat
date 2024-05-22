package controller;

import java.io.*;
import java.net.Socket;

public class SendingThread extends Thread {
    private Socket socket;
    private PrintWriter serverWriterOutputStream;
    private BufferedReader keyboard;

    public SendingThread(Socket socket) {
        this.socket = socket;
        try{
            serverWriterOutputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            keyboard = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsgFromKeyboard() {
        try {
            System.out.print("Enter message for server: ");
            sendMsg(keyboard.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String message) {
        serverWriterOutputStream.println(message);
        serverWriterOutputStream.flush();
    }

    @Override
    public void run() {
        while(true) {
            sendMsgFromKeyboard();
        }
    }
}
