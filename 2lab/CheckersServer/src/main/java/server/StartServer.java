package server;

import model.Player;
import model.Players;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class StartServer {
    private static Logger log = Logger.getLogger(StartServer.class.getName());

    public static void main(String[] args) throws IOException {
        Players players  = new Players();
        DataInputStream dataInputStream;
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);

        try {
            while (true) {
                System.out.println("Waiting for client");
                Socket socket = serverSocket.accept();
                try {
                    new Game(socket, players);
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    players.addPlayer(new Player(socket.getInetAddress(),dataInputStream.readUTF(),5));
                } catch (Exception ex) {
                    log.error(ex);
                } finally {
                    socket.close();
                }
            }

        } finally {
            serverSocket.close();
        }
    }
}
