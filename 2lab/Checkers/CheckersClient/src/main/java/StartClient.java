import models.Player;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class StartClient {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(StartClient.class.getName());

        int serverPort = 4444;
        String address = "127.0.0.2";

        try {
            InetAddress ipAddress = InetAddress.getByName(address);

            Socket socket = new Socket(ipAddress, serverPort);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Login: ");

            objectOutputStream.writeObject(new Player("name"/*keyboard.readLine()*/, 0));
            objectOutputStream.flush();

            Game game = new Game(objectOutputStream, objectInputStream, keyboard);
            game.run();
        } catch (UnknownHostException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }

}
