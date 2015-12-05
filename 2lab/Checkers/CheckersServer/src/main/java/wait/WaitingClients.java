package wait;

import org.apache.log4j.Logger;
import sessions.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class WaitingClients extends Thread {
    private static Logger log;
    private int port;
    private ServerSocket serverSocket;

    public WaitingClients() {
        log = Logger.getLogger(WaitingClients.class.getName());
        port = 4444;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for client");
                Socket socket = serverSocket.accept();
                new Session(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
               log.error(e);
            }
        }
    }
}
