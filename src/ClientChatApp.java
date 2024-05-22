import controller.Controller;
import controller.ReadingThread;
import controller.SendingThread;
import gui.ChatApp;

import java.io.IOException;
import java.net.Socket;

public class ClientChatApp {

    public static void main(String[] args) throws IOException {
//        Controller controller = new Controller();
//        controller.handleCommunication();

        int port = 3535;
        String address = "localhost";
        Socket socket = new Socket(address, port);

        SendingThread sendingThread = new SendingThread(socket);
        ChatApp chatApp = new ChatApp(sendingThread);
        ReadingThread readingThread = new ReadingThread(socket, chatApp);

        sendingThread.start();
        readingThread.start();


    }

}
