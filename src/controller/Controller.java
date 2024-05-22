package controller;

import java.io.*;
import java.net.Socket;

@Deprecated
public class Controller {
    private int port = 3535;
    private String address = "localhost";
    private Socket socket;
    private BufferedReader serverReaderInputStream;
    private PrintWriter serverWriterOutputStream;
    private BufferedReader keyboard;

    public Controller() {
        try {
            this.socket = new Socket(address, port);
            serverWriterOutputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            serverReaderInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            keyboard = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCommunication() {
        while (true) {
            sendMsgFromKeyboard(); // in one thread
            String msgFromServer = readMsgFromServer(); // in another thread
            System.out.println("Msg from server: " + msgFromServer);
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
}
