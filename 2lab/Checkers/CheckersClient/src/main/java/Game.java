import model.Move;
import model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Дмитрий on 16.11.2015.
 */
public class Game {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private BufferedReader keyboard;

    public Game(ObjectOutputStream outputStream, ObjectInputStream inputStream, BufferedReader keyboard) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.keyboard = keyboard;
    }

    public void run() {
        Player startPlayer = null;
        try {
            startPlayer = (Player) inputStream.readObject();
            if (!startPlayer.isMove()) {
                while (true) {
                    outputStream.writeObject(enterMove());
                    System.out.println(inputStream.readObject());
                }
            } else {
                while (true) {
                    System.out.println(inputStream.readObject());
                    outputStream.writeObject(enterMove());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Move enterMove() throws IOException {
        Move move = new Move();
        System.out.println("Enter From: ");
        move.setFrom(keyboard.readLine());
        System.out.println("Enter to: ");
        move.setTo(keyboard.readLine());
        return move;
    }

}
