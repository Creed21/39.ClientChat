package controller;

import java.io.*;
import java.net.Socket;

public class Controller {
    private int port = 3535;
    private String address = "localhost";
    private Socket socket;
    private BufferedReader serverReaderInputStream;
    private PrintWriter serverWriterOutputStream;
    private BufferedReader keyboard;

    /**
     * ****** napomena ******
     *      AKO NA SERVERSKOJ STRANI PORVO INICIJAZIUJETE
     *
     *      BufferedReader pa onda PrintWriter
     *      NA KLIJENTSKOJ STRANI MORA BITI OBRNUTO
     *
     *      PrintWriter pa onda BufferedReader
     */
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
        sendMsgFromKeyboard();
        try {
            String readingStatus = readMsgFromServer();
            String msgFromServer = readMsgFromServer();

            System.out.println("Server reading status: " + readingStatus);
            System.out.println("Msg from server: "+ msgFromServer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readMsgFromServer() throws IOException {
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

    private void sendMsg(String message) {
        serverWriterOutputStream.println(message);
        serverWriterOutputStream.flush();
    }
}
