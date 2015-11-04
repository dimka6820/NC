package server;

import model.Players;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class Game extends Thread {
    private Socket socket;
    private Players players;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final Logger log = Logger.getLogger(StartServer.class.getName());


    public Game(Socket socket, Players players) throws IOException {
        this.socket = socket;
        this.players = players;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        start();
    }

    @Override
    public void run() {
//        String line = null;
//        try {
//            while (true) {
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
