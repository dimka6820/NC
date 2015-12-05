import models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Дмитрий on 06.12.2015.
 */
public class GameTest {
    Thread runServer;
    int serverPort = 4444;
    String address = "127.0.0.2";

    @Before
    public void setup() {
        runServer = new Thread() {
            public void run() {
                Start.main(null);
            }
        };
        runServer.start();
    }

    @Test
    public void testRun() throws Exception {
        Game player1 = newGame();
        Game player2 = newGame();
    }

    private Game newGame() throws IOException {


        InetAddress ipAddress = InetAddress.getByName(address);

        Socket socket = new Socket(ipAddress, serverPort);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        objectOutputStream.writeObject(new Player("name", 0));
        objectOutputStream.flush();

        return new Game(objectOutputStream, objectInputStream, keyboard);
    }

    @After
    public void dieThreadServer(){
        runServer.stop();
    }
}