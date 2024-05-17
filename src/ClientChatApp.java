import controller.Controller;
import controller.ReadingThread;
import controller.SendingThread;

import java.io.IOException;
import java.net.Socket;

public class ClientChatApp {

    public static void main(String[] args) throws IOException {
//        Controller controller = new Controller();
//        controller.handleCommunication();

        int port = 3535;
        String address = "localhost";
        Socket socket = new Socket(address, port);

        Thread sendingThread = new SendingThread(socket);
        Thread readingThread = new ReadingThread(socket);

        sendingThread.start();
        readingThread.start();

    }

}
